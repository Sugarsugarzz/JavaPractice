package com.sugar.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    // sentinel一般只能入侵到controller，加了注解后，能够对service进行保护
    @SentinelResource("test")
    public void test() {
        System.out.println("test");
    }
}
