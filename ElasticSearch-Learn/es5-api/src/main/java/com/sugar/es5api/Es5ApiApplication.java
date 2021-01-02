package com.sugar.es5api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration.class})
public class Es5ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Es5ApiApplication.class, args);
	}

}
