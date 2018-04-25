package com.currencyconverter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.currencyconverter.controller",
		"com.currencyconverter.service.*",
		"com.currencyconverter.infrastructure.*"})
public class MyApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        System.out.println("Running application...");
    }
}
