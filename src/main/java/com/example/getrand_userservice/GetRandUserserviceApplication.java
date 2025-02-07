package com.example.getrand_userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GetRandUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetRandUserserviceApplication.class, args);
    }

}
