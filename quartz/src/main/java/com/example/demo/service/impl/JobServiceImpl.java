package com.example.demo.service.impl;

import com.example.demo.common.ClassFunction;
import com.example.demo.dto.JobDto;
import com.example.demo.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        if(!scheduler.isShutdown()){
            scheduler.start(); //启动
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void deleteJob(JobDto jobDto) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getName(), jobDto.getGroupName());
        JobKey jobKey = JobKey.jobKey(jobDto.getName(), jobDto.getGroupName());
        scheduler.pauseTrigger(triggerKey);//停止触发器
        scheduler.unscheduleJob(triggerKey);//移除触发器
        scheduler.deleteJob(JobKey.jobKey(jobDto.getName(), jobDto.getGroupName()));//删除任务

    }

    /**
     * 停止任务
     * @param jobDto
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(JobDto jobDto) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobDto.getName(), jobDto.getGroupName()));

    }

    /**
     * 恢复任务
     * @param jobDto
     * @throws SchedulerException
     */
    @Override
    public void resumeJob(JobDto jobDto) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobDto.getName(), jobDto.getGroupName() ));
    }


    /**
     * 修改任务触发时间
     * @param jobDto
     * @throws SchedulerException
     */
    @Override
    public void update(JobDto jobDto) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getName(), jobDto.getGroupName());
        if(null == triggerKey){
            return;
        }
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if(null == trigger){
            return;
        }
        String oldTime = trigger.getCronExpression();
        if(!oldTime.equalsIgnoreCase(jobDto.getCronExpression())){
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobDto.getCronExpression());
            trigger = trigger.getTriggerBuilder()
                .withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder)
                .build();
            scheduler.rescheduleJob(triggerKey,trigger);
        }
    }

    @Override
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdownAllJobs() {

        try {
            if(!scheduler.isInStandbyMode()){
                scheduler.standby();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


}
