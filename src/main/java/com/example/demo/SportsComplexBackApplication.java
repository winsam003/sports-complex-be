package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaAuditing // BaseEntity를 참조 / EntityListeners 를 사용하려면 이 처리를 해줘야 함
public class SportsComplexBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsComplexBackApplication.class, args);
	}

}
