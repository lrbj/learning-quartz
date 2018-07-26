package com.example.batchsquartz;

import ch.qos.logback.core.spi.ScanException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author: Kayla,Ye
 * @Description: Trigger 触发器
 * @Date:Created in 3:30 PM 7/26/2018
 */
@Component("CronTriggerFactoryBean")
public class CronTriggerFactoryBean {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //修改或者添加一个定时任务
    public void createNewTask(String expression , int  taskId) throws ScanException{
        TriggerKey triggerKey = TriggerKey.triggerKey ( "TASK-"+ taskId, "JOB-"+taskId )
    }


}
