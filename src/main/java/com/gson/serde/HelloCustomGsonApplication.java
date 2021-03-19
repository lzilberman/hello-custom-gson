package com.gson.serde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloCustomGsonApplication {
	@Bean
	public AppRunner startRunner () {
		return new AppRunner ();  
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloCustomGsonApplication.class, args);
	}

}
