package com.example.batchsquartz;

import org.quartz.SchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@SpringBootApplication
@PropertySource(value = {"./application.properties" })
public class BatchsquartzApplication {

    public  static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        SpringApplication.run ( BatchsquartzApplication.class,args );
    }

    @Bean
    public SchedulerFactoryBean  schedulerFactoryBean() throws  Exception{
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean ();
        Properties  quartzProperties = new Properties ( );

        FileInputStream in = new FileInputStream ( ResourceUtils.getFile ("classpath:quartz.properties" )) ;
        quartzProperties.load ( in );

        schedulerFactoryBean.setQuartzProperties ( quartzProperties );
        return  schedulerFactoryBean;
    }

    @Bean
    public  MyApplicationContextUtil myApplicationContextUtil(){
        return new MyApplicationContextUtil ();
    }
}
