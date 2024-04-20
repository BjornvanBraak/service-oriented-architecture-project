package com.example.ui.controller;

import com.example.ui.exception.ServiceNotFound;
import com.example.ui.helpers.DomainResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
            URI domain = domainResolver.getUrl("iamService");
            URI endpoint = UriComponentsBuilder.fromUri(domain).path("/test/online").build().toUri();
            System.out.println(endpoint);
            String result = restTemplate.getForObject(endpoint, String.class);
            return result;
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    private RedirectView loginAttempt(){
        return new RedirectView("/");
    }
}
