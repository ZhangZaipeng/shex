package com.exchange.api.conf.mybatis;

import com.exchange.platform.mybatis.DefaultMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.contexchanget.annotation.Bean;
import org.springframework.contexchanget.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
@MapperScan("com.exchange")
@EnableTransactionManagement
public class MybatisMapperScannerConfig {

  //@Bean
  public MapperScannerConfigurer mapperScannerConfigurer(){
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    mapperScannerConfigurer.setBasePackage("com.exchange");
    mapperScannerConfigurer.setMarkerInterface(DefaultMapper.class);
    return mapperScannerConfigurer;
  }


}
