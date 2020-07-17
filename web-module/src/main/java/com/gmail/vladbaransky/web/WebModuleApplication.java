package com.gmail.vladbaransky.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication(scanBasePackages = {
        "com.gmail.vladbaransky.web",
        "com.gmail.vladbaransky.service",
        "com.gmail.vladbaransky.repository"})
public class WebModuleApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(WebModuleApplication.class, args);

    }

}
