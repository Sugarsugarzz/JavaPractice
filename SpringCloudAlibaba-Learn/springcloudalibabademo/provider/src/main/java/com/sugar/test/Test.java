package com.sugar.test;

import org.springframework.web.client.RestTemplate;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            restTemplate.getForObject("http://localhost:8081/index", String.class);
            Thread.sleep(200);
        }
    }
}
