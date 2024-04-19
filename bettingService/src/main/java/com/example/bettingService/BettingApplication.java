package com.example.bettingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class BettingApplication {
	public static void main(String[] args) {
		SpringApplication.run(BettingApplication.class, args);
	}
}
