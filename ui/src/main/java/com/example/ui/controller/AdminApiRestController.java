package com.example.ui.controller;

import com.example.ui.entity.CustomerResponse;
import com.example.ui.exception.ServiceNotFound;
import com.example.ui.helpers.DomainResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminApiRestController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DomainResolver domainResolver;

    public List<CustomerResponse> getCustomers(){
        try {
            URI url = domainResolver.getUrl("iamService", "/api/v1/customers");
            ResponseEntity<List<CustomerResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerResponse>>(){});
            return response.getBody();
        } catch (ServiceNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
