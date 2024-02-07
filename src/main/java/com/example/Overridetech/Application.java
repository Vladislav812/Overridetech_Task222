package com.example.Overridetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "applicationProperties");
        SpringApplication.run(Application.class, args);
    }

}
