package com.example.cloudusage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.cloudusage")
public class CloudUsageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudUsageApplication.class, args);
    }
}
