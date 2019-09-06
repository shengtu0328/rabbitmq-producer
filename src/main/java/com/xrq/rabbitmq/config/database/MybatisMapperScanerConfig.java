package com.xrq.rabbitmq.config.database;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rabbitmq-producer
 * @description:
 * @author: rqxiao
 * @create: 2018-09-06 16:52
 **/
@Configuration
@AutoConfigureAfter(MybatisDataSourceConfig.class)//在 MybatisDataSourceConfig 加载完
public class MybatisMapperScanerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.xrq.rabbitmq.mapper");
        return mapperScannerConfigurer;
    }

}

