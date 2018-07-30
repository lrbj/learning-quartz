package com.example.batchsquartz;

import ch.qos.logback.core.spi.ScanException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import java.util.Date;


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
    public void createNewTask(String expression , int  taskId) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey ( "TASK-"+ taskId, "JOB-"+taskId );
        CronTrigger trigger = null;


        JobKey jobKey = new JobKey ( "TASK-"+ taskId, "JOB-"+taskId);
        JobDetail jobDetail = JobBuilder.newJob(SpringQuartzJob.class).withIdentity(jobKey).build();

        //任务基础信息
        jobDetail.getJobDataMap().put("taskId", taskId);

        //表达是调度构建器
        CronScheduleBuilder cronScheduleBuilder = null;
        cronScheduleBuilder = CronScheduleBuilder.cronSchedule(expression);

        //按照cronExresssion表达式构建一个新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startAt(new Date()).withSchedule(cronScheduleBuilder).build();

        //加入任务队列
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.rescheduleJob(triggerKey,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace ();
        }

    }


}
