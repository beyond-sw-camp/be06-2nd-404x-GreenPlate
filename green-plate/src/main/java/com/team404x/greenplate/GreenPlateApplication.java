package com.team404x.greenplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GreenPlateApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenPlateApplication.class, args);
	}

}
