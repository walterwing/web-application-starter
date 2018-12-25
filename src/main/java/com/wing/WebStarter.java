package com.wing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebStarter {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebStarter.class);
		app.run(args);
	}

}
