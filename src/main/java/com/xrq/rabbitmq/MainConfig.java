package com.xrq.rabbitmq;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rabbitmq-producer
 * @description: 扫描项目中所用到的包
 * @author: rqxiao
 * @create: 2018-09-06 14:29
 **/
@Configuration
@ComponentScan({"com.xrq.rabbitmq.*"})
public class MainConfig {
}
