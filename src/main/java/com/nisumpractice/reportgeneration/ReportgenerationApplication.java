package com.nisumpractice.reportgeneration;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportgenerationApplication {
	@Bean
	public Faker faker() {
		return new Faker();
	}
	public static void main(String[] args) {
		SpringApplication.run(ReportgenerationApplication.class, args);
	}

}
