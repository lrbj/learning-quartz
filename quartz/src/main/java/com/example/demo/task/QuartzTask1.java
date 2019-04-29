package com.example.demo.task;

import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:46 PM 1/24/2019
 */
@Component
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class QuartzTask1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行业务处理task1：" + new Date());
    }
}
