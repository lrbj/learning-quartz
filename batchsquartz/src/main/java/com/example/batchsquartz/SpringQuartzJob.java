package com.example.batchsquartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.naming.Context;

/**
 * @Author: Kayla,Ye
 * @Description:
 * @Date:Created in 9:58 AM 7/27/2018
 */
@Component("SpringQuartzJob")
public class SpringQuartzJob extends QuartzJobBean {

    @Autowired
    Job importUserJob;
    @Autowired
    private JobLauncher jobLauncher;
    @Override
    public void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        System.out.println("TestJobStart:" + Thread.currentThread().getId());
        try {
            init();
            JobParameters jobParameters= new JobParametersBuilder ().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution result =jobLauncher.run(importUserJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Job1End");
    }

    public void init() {
        importUserJob =(Job) MyApplicationContextUtil.getBeanObj("importUserJob");
        jobLauncher =(JobLauncher) MyApplicationContextUtil.getBeanObj("jobLauncher",JobLauncher.class);
    }


}
