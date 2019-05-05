package com.example.demo.job;

import org.quartz.*;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:46 PM 1/24/2019
 */
public class QuartzTask1 implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行业务处理task1：" + new Date());
    }
}
