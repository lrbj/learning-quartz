package com.example.demo.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:01 PM 4/30/2019
 */
@Component
public class HellworldJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello World!:" + new Date());
    }
}
