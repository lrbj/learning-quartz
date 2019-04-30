package com.example.demo;

import com.example.demo.job.QuartzTask1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private  Scheduler scheduler;
    @Test
    public void test(){
        try {
         //3、创建JobDetail
            JobDetail  jobDetail = JobBuilder.newJob( QuartzTask1.class)
                    .withDescription("测试的定时任务。")//job的描述
                    .withIdentity("test111", "testgroup")//任务job和name 和group,各自的name 和group必须唯一
                    .build();

            //任务运行的时间
            long time = System.currentTimeMillis() + 2*1000L;//3秒后启动

            Date statime = new Date(time);

            //4、创建Trigger
            Trigger t = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("test","testgroup") //Trigger 的name和group必须唯一
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
