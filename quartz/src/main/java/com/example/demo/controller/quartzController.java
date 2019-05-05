package com.example.demo.controller;

import com.example.demo.dto.JobDto;
import com.example.demo.job.QuartzTask1;
import com.example.demo.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    Scheduler scheduler;

    @Autowired
    JobService jobService;

    @PostMapping
    @ApiOperation(value = "一个完整实例")
    public void  quartzTest(){
        try {
            //3、创建JobDetail
            JobDetail jobDetail = JobBuilder.newJob( QuartzTask1.class)
                    .withDescription("测试的定时任务。")//job的描述
                    .withIdentity("test3", "testgroup3")//任务job和name 和group
                    .build();

            //任务运行的时间
            long time = System.currentTimeMillis() + 2*1000L;//3秒后启动

            Date statime = new Date(time);

            //4、创建Trigger
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("test3","testgroup3")
                    .startAt(statime)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();//每两秒执行一次



            //5、启动调度器
            scheduler.start();

            //6、注册任务和定时器
            scheduler.scheduleJob(jobDetail, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加工作任务")
    public void addJob(@RequestBody(required = true)JobDto jobDto) throws Exception {
        jobService.addJob(jobDto);
    }


    @PatchMapping("/update")
    @ApiOperation(value = "更新工作任务时间")
    public  void updateJob(@RequestBody(required = true) JobDto jobDto) throws SchedulerException {
        jobService.update(jobDto);
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "移除工作任务")
    public  void removeJob(@RequestBody(required = true) JobDto jobDto) throws SchedulerException {
        jobService.deleteJob(jobDto);
    }

    @PostMapping("/pause")
    @ApiOperation(value = "暂停工作任务")
    public  void pauseJob(@RequestBody(required = true) JobDto jobDto) throws SchedulerException {
        jobService.pauseJob(jobDto);
    }


    @PostMapping("/resume")
    @ApiOperation(value = "恢复工作任务")
    public void resume(@RequestBody(required = true) JobDto jobDto) throws SchedulerException {
        jobService.resumeJob(jobDto);
    }

    @PostMapping("/startAll")
    @ApiOperation(value = "启动所有任务")
    public void startAll(){
        jobService.startAllJobs();
    }

    @PostMapping("/shutdownAll")
    @ApiOperation(value = "关闭所有任务")
    public void shutdownAll(){
        jobService.shutdownAllJobs();
    }
}
