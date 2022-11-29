package com.test.task.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FrontendBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(FrontendBootstrap.class);
    }
}
