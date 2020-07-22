package com.ss.training.utopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TravelerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelerApplication.class, args);
	}
}
