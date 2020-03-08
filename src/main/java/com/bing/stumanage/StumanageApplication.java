package com.bing.stumanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing// 启用springdata JPA审计
public class StumanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StumanageApplication.class, args);
	}

}
