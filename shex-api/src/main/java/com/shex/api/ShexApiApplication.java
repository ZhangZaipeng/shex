package com.shex.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.shex"})
// @MapperScan("com.shex")
// 启动注解事务
@EnableTransactionManagement
public class ShexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShexApiApplication.class, args);
	}
}
