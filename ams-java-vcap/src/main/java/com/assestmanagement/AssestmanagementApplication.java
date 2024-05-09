package com.assestmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assestmanagement")
public class AssestmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssestmanagementApplication.class, args);
	}
	

}
