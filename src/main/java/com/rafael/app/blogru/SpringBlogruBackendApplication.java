package com.rafael.app.blogru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringBlogruBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogruBackendApplication.class, args);
	}

}
