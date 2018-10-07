package com.exchange.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.exchange")
// @MapperScan("com.exchange")
// 启动注解事务
// @EnableTransactionManagement
public class ExchangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApiApplication.class, args);
	}
}
