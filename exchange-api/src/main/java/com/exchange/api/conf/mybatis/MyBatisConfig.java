package com.exchange.api.conf.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.exchange.platform.mybatis.DefaultMapper;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.contexchanget.properties.ConfigurationProperties;
import org.springframework.contexchanget.annotation.Bean;
import org.springframework.contexchanget.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhangzp on 2018/8/27.
 *
 */
@Configuration
public class MyBatisConfig {
   private static final String MAPPER_LOCATION = "classpath:/com/exchange/**/mapper/*.ibatis.xml";

    //@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
      return new com.alibaba.druid.pool.DruidDataSource();
    }

    //@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory()
            throws exchangeception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }


    /*@Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClass());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUser());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.75.95.136:3306/test?autoReconnect=true&amp;useUnicode=true&amp;" +
                "characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("test");
        dataSource.setPassword("LxY7wQfJs_jsX");
        return dataSource;
    }*/
}
