package com.shex.api.conf.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhangzp on 2018/8/27.
 *
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {
   /*private static final String MAPPER_LOCATION = "classpath:/com/shex*//**//*mapper*//*.ibatis.xml";

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer () {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.shex");
        mapperScannerConfigurer.setMarkerInterface(DefaultMapper.class);

        return mapperScannerConfigurer;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataSource")
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
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/
}
