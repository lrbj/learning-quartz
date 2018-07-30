package com.example.batchsquartz;

import ch.qos.logback.core.spi.ScanException;
import org.quartz.SchedulerConfigException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kayla,Ye
 * @Description:
 * @Date:Created in 10:09 AM 7/30/2018
 */
@RestController
@ComponentScan(basePackages = {"com.example.batchsquartz"})
@RequestMapping("/quartz")
public class QuartzResource {

    @Autowired
    private  CronTriggerFactoryBean  triggerFactoryBean;

    final  int CREATE_ID = 17;

    @GetMapping(value = "/get/{taskId}")
    public  void  createTask(@PathVariable("taskId") String taskId) throws SchedulerException {
        String str[] = taskId.split ( "," );
        for (int i = 0; i < str.length; i++ ){
            int taskIdx = Integer.parseInt ( str[i] );
            triggerFactoryBean.createNewTask("00/1 * * * ?", 1);
        }

    }

}
