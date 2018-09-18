package com.shex.api.conf.mybatis;

import com.shex.platform.mybatis.DefaultMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
@MapperScan("com.shex")
@EnableTransactionManagement
public class MybatisMapperScannerConfig {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer(){
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    mapperScannerConfigurer.setBasePackage("com.shex");
    mapperScannerConfigurer.setMarkerInterface(DefaultMapper.class);
    return mapperScannerConfigurer;
  }


}
