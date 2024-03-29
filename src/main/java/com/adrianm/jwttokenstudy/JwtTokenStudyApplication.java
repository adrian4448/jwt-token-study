package com.adrianm.jwttokenstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class JwtTokenStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenStudyApplication.class, args);
	}

}
