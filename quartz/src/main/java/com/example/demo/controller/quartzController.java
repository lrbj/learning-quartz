package com.example.demo.controller;

import com.example.demo.task.QuartzTask1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:24 PM 4/29/2019
 */
@RestController
@RequestMapping("/api/quartz")
@Api(tags = "定时器接口")
public class quartzController {
    @PostMapping
    @ApiOperation(value = "一个完整实例")
    public void  quartzTest(){
        try {
            //1、创建scheduler的工厂
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            //2、从工厂中获取调度器实例
            Scheduler scheduler = schedulerFactory.getScheduler();
            System.out.println("test scheduler");

            //3、创建JobDetail
            JobDetail jobDetail = JobBuilder.newJob( QuartzTask1.class)
                    .withDescription("测试的定时任务。")//job的描述
                    .withIdentity("test", "testgroup")//任务job和name 和group
                    .build();

            //任务运行的时间
            long time = System.currentTimeMillis() + 2*1000L;//3秒后启动

            Date statime = new Date(time);

            //4、创建Trigger
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("test","testgroup")
                    .startAt(statime)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();//每两秒执行一次

            //5、注册任务和定时器
            scheduler.scheduleJob(jobDetail, t);

            //6、启动调度器
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
