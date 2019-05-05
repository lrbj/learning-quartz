package com.example.demo.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:01 PM 4/30/2019
 */
public class HellworldJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello World!!!:" + new Date());
        System.out.println("this is a job");
    }
}
