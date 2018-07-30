package com.example.demo.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Kayla,Ye
 * @Description:  spring 自带的任务框架,同一个task,如果前一个还没跑完，后面一个就不会触发
 * @Date:Created in 4:08 PM 7/30/2018
 */
@Component
@EnableScheduling
public class SpringScheduleTask {

    /**
     * 每分钟执行一次
     */
    @Scheduled( cron = "0 0/1 * * * ?")
    public void  reptlian(){
        System.out.println ("执行调度任务：" + new Date ());
    }
}
