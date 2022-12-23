package com.springboot.mywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class MywebappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MywebappApplication.class, args);
	}

}
