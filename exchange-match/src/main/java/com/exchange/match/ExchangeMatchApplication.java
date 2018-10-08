package com.exchange.match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/10/8.
 */
@SpringBootApplication(scanBasePackages = "com.exchange")
public class ExchangeMatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeMatchApplication.class, args);
    }
}
