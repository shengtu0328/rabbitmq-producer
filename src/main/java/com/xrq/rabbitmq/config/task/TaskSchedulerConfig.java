package com.xrq.rabbitmq.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling//spring的注解 启用定时任务
public class TaskSchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler()); //使用一个线程池
    }

    @Bean(destroyMethod="shutdown") //服务关闭时结束线程池
    public Executor taskScheduler(){
        return Executors.newScheduledThreadPool(100);
    }

}


