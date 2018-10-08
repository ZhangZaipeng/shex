package com.exchange.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/10/8.
 */
@SpringBootApplication(scanBasePackages = "com.exchange")
public class ExchangeTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeTaskApplication.class, args);
    }
}
