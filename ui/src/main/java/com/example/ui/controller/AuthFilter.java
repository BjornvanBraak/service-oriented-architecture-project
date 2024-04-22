package com.example.ui.controller;

import com.example.ui.exception.ServiceNotFound;
import com.example.ui.helpers.DomainResolver;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@Component
@Order(2)
@Slf4j
public class AuthFilter implements Filter {

    @Autowired
    DomainResolver domainResolver;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(Objects.equals(servletRequest.getScheme(), "http") || Objects.equals(servletRequest.getScheme(), "https")){
            String path = ((HttpServletRequest) servletRequest).getServletPath();
            if(!Objects.equals(path, "/login")){
                Cookie sessionTokenCookie = WebUtils.getCookie(((HttpServletRequest) servletRequest), "sessionToken");
                assert sessionTokenCookie != null;
                String sessionToken = sessionTokenCookie.getValue();
                System.out.println(sessionToken);
                try {
                    URI url = domainResolver.getUrl("iamService", "/api/v1/auth/token/" + sessionToken);
                    Boolean isAuth = restTemplate.getForObject(url, Boolean.class);
                    if(!isAuth){
                        log.warn("Unauthenticated users request resources at path: " + path);
                        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                        return;
                    }
                } catch (ServiceNotFound e) {
                    throw new RuntimeException(e);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

//    private HttpServletResponse createBlockServletResponse(HttpServletResponse response){
//        response.reset();
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        return response;
//    }
}
