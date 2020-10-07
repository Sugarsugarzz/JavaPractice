package com.sugar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

// 开启异步注解功能
@EnableAsync
@SpringBootApplication
public class Springboot09TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09TaskApplication.class, args);
	}

}
