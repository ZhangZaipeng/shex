package com.exchange.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/10/8.
 */
@SpringBootApplication(scanBasePackages = "com.exchange")
public class ExchangeWalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeWalletApplication.class, args);
    }
}
