package com.example.ui.controller;

import com.example.ui.entity.BetResponse;
import com.example.ui.entity.CustomerResponse;
import com.example.ui.entity.LoginAttempt;
import com.example.ui.entity.LoginAttemptResponse;
import com.example.ui.exception.ServiceNotFound;
import com.example.ui.helpers.DomainResolver;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/api-gateway")
public class APIGatewayRestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DomainResolver domainResolver;

    public LoginAttemptResponse verifyLoginAttempt(LoginAttempt loginAttempt){
        try {
            URI url = domainResolver.getUrl("iamService", "/api/v1/auth");
            LoginAttemptResponse loginAttemptResponse = restTemplate.postForObject(url, loginAttempt, LoginAttemptResponse.class);
           return loginAttemptResponse;
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This would depend on the real world auth methods used. In our simple case we make use of session tokens, the AuthService should be contacted to invalidate the token
     * @return
     */
    public Boolean logout(Long customerId){
        try {
            URI url = domainResolver.getUrl("iamService", "/api/v1/auth/logout/" + customerId);
            restTemplate.delete(url);
            return true;
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }

    public List<BetResponse> getBets(Long customerId){
        try {
            URI url = domainResolver.getUrl("bettingService", "/api/v1/bets/customers/" + customerId);
            ResponseEntity<List<BetResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<BetResponse>>(){});
            return response.getBody();
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
