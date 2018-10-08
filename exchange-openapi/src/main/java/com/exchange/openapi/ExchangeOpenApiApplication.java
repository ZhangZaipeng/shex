package com.exchange.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/10/8.
 */
@SpringBootApplication(scanBasePackages = "com.exchange")
public class ExchangeOpenApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeOpenApiApplication.class, args);
    }
}
