package com.example.demo.service.impl;

import com.example.demo.common.ClassFunction;
import com.example.demo.dto.JobDto;
import com.example.demo.service.JobService;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:20 PM 4/30/2019
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(JobDto jobDto) throws Exception {

        //构建jobDetail
        JobDetail jobDetail = JobBuilder.newJob(ClassFunction.getJobClass(jobDto.getJobClassName()).getClass())
                .withIdentity(jobDto.getName(),jobDto.getGroupName())
                .build();


        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobDto.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobDto.getName(), jobDto.getGroupName())
                .withSchedule(cronScheduleBuilder)
                .build();

        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void deleteJob(JobDto jobDto) throws SchedulerException {

        scheduler.pauseTrigger(TriggerKey.triggerKey(jobDto.getName(), jobDto.getGroupName()));//暂停触发器
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobDto.getName(),jobDto.getGroupName()));//从调度中移除
        scheduler.deleteJob(JobKey.jobKey(jobDto.getName(), jobDto.getGroupName()));//删除任务

    }

    @Override
    public void pauseJob(JobDto jobDto) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobDto.getJobClassName(), jobDto.getGroupName()));

    }

    @Override
    public void resumeJob(JobDto jobDto) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobDto.getName(), jobDto.getGroupName() ));
    }

    @Override
    public void update(JobDto jobDto) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getName(), jobDto.getGroupName());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobDto.getCronExpression());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey,trigger);


        }

    }
}
