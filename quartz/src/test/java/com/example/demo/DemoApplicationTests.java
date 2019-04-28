package com.example.demo;

import com.example.demo.task.QuartzTask1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void test(){
        try {
        //1、创建scheduler的工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //2、从工厂中获取调度器实例
            Scheduler scheduler = schedulerFactory.getScheduler();

         //3、创建JobDetail
            JobDetail  jobDetail = JobBuilder.newJob( QuartzTask1.class)
                    .withDescription("测试的定时任务。")//job的描述
                    .withIdentity("test1", "test1group")//任务job和name 和group
                    .build();

            //任务运行的时间
            long time = System.currentTimeMillis() + 2*1000L;//3秒后启动

            Date statime = new Date(time);

            //4、创建Trigger
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("test1","test1group")
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
