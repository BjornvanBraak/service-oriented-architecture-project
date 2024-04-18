package com.example.gameService.controller;

//import com.example.gameService.config.ExternalConsulConfig;
import com.example.gameService.entity.ProductResponse;
import com.example.gameService.repository.ProductRepository;
import com.github.loki4j.slf4j.marker.LabelMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestRestController {
    private final RestTemplate restTemplate;
//    @Autowired
//    private ExternalConsulConfig externalConsulConfig;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductRepository testRepository;


//    @Value("${spring.cloud.consul.discovery.instance-id}")
//    private String appInstance;

    //dependency injection of restTemplate bean in RestTemplateConfiguration
    public TestRestController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @GetMapping("/online")
    private String test(){
        return "testApp online";
    }

    @GetMapping("/logging")
    private String testLogging(){
        LabelMarker marker = LabelMarker.of("test", () ->
                "test-value"); // (1)
        log.info(marker, "Testing the logger");
        return "logging online with marker";
    }

//    @GetMapping("/instance")
//    private String instance(){
//        log.info("Instance request");
//        return appInstance;
//    }

    @GetMapping("/external-request")
    private String testExternalRequest(){
        String url = "http://:8081/test-service/instance";
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String response = restTemplate.getForObject(url, String.class);
        return "external app called with instance id: " + response;
    }

//    @GetMapping("/consul/service-discovery")
//    private String testConsulServiceDiscovery(){
//        log.info("Service discovery request");
//        String url = getServiceURL("testApp", "/test-service/instance");
//        String response = restTemplate.getForObject(url, String.class);
//        return "service app called with instance id: " + response;
//    }
//
//    @GetMapping("/consul/client-service-discovery")
//    private String testConsulClientServiceDiscovery(){
//
//        return discoveryClient.getInstances("testApp").toString();
//    }
//
//    private String getServiceURL(String serviceId,String serviceEndpoint){
//        return new StringBuffer("http://").append(serviceId)
//                .append(serviceEndpoint).toString();
//    }

//    @GetMapping("/consul/kv")
//    private String testConsulKV(){
//        return externalConsulConfig.testConsulKV();
//    }

    @GetMapping("/database")
    private ProductResponse testDatabase(){
        return new ProductResponse(testRepository.findAll());
    }

}
