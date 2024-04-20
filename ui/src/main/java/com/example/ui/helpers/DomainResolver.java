package com.example.ui.helpers;

import com.example.ui.config.DomainProperties;
import com.example.ui.exception.ServiceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@Component
public class DomainResolver {
    @Autowired
    private DomainProperties domainProperties;

    /**
     * Returns an URI object based on the application.yml configuration.
     * @param serviceName
     * @return
     * @throws ServiceNotFound
     */
    public URI getUrl(String serviceName) throws ServiceNotFound {
        Map<String, String> domainsMap = domainProperties.getDomainAddresses();
        if(!domainsMap.containsKey(serviceName)){
            throw new ServiceNotFound("Mapping from service name to domain was not found for service: " + serviceName);
        }
        String domain = domainsMap.get(serviceName);
        URI url = null;
        try {
            url = new URI("http://" + domain);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
}
