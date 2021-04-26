package com.zuhlke.apachecalcitedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = {H2ConsoleAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class ApacheCalciteDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheCalciteDemoApplication.class, args);
    }
}