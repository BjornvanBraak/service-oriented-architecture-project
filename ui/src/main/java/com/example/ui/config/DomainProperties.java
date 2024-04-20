package com.example.ui.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix= "external-services")
@Getter
@Setter
public class DomainProperties {
    private Map<String, String> domainAddresses;
}
