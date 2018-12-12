package com.wing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WebStarter {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebStarter.class);
		app.run(args);
	}

}
