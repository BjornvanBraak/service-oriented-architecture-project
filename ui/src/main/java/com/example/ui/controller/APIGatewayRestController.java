package com.example.ui.controller;

import com.example.ui.entity.CustomerResponse;
import com.example.ui.entity.LoginAttempt;
import com.example.ui.entity.LoginAttemptResponse;
import com.example.ui.exception.ServiceNotFound;
import com.example.ui.helpers.DomainResolver;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/api-gateway")
public class APIGatewayRestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DomainResolver domainResolver;

    @GetMapping("/test")
    private Object test(){
        try {
            URI endpoint = domainResolver.getUrl("iamService", "/test/online");
            String result = restTemplate.getForObject(endpoint, String.class);
            return result;
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }

    public LoginAttemptResponse verifyLoginAttempt(LoginAttempt loginAttempt){
        try {
            URI url = domainResolver.getUrl("iamService", "/api/v1/auth");
            CustomerResponse customer = restTemplate.postForObject(url, loginAttempt, CustomerResponse.class);
            if(customer == null){
                return new LoginAttemptResponse(false, null);
            }
            return new LoginAttemptResponse(true, customer);
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This would depend on the real world auth methods used. In our simple case we make use of session tokens, the AuthService should be contacted to invalidate the token
     * @return
     */
    public Boolean logout(){
        return true;
    }
}
