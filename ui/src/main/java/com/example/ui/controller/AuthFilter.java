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
            log.info("Requesting resources from ui at path: " + path);
            if(isProtectedPath(path)){
                Cookie sessionTokenCookie = WebUtils.getCookie(((HttpServletRequest) servletRequest), "sessionToken");
                System.out.println(sessionTokenCookie);
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
        } else {
            //any traffic other than http is not protected for the demo
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private boolean isProtectedPath(String path){
        System.out.print(path);
        System.out.println("path is equal to login: " + Objects.equals(path, "/login"));
        System.out.println("path is equal to metrics: " + Objects.equals(path, "/actuator/prometheus"));
//        System.out.println("path is equal to metrics: " + Objects.equals(path, "/admin"));
        //for the demo we do not protect the scraping endpoint of prometheus
        if(Objects.equals(path, "/login") || Objects.equals(path, "/actuator/prometheus")){
            return false;
        }
        return true;
    }

//    private HttpServletResponse createBlockServletResponse(HttpServletResponse response){
//        response.reset();
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        return response;
//    }
}
