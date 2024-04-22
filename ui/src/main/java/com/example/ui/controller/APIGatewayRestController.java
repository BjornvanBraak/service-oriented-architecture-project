package com.example.ui.controller;

import com.example.ui.entity.*;
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
import java.net.URISyntaxException;
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

    public List<GameResponse> getOpenGames(){
        URI base_url = null;
        try {
            base_url = domainResolver.getUrl("gameService", "/api/v1/games");
            String base_query = base_url.getQuery();
            String query;
            if (base_query == null){
                query = "open=true";
            } else {
                query = base_query + "&" + "open=true";
            }
            URI url = new URI(base_url.getScheme(), base_url.getAuthority(), base_url.getPath(), query, base_url.getFragment());
            ResponseEntity<List<GameResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GameResponse>>(){});
            return response.getBody();
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public BetResponse placeBet(CreateBetAttempt createBetAttempt){
        URI url = null;
        try {
            url = domainResolver.getUrl("bettingService", "/api/v1/bets");
            BetResponse response = restTemplate.postForObject(url, createBetAttempt, BetResponse.class);
            return response;
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
