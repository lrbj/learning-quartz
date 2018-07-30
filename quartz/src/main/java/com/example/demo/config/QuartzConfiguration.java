package com.example.demo.config;

import com.example.demo.task.QuartzTask;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: Kayla,Ye
 * @Description: 配置任务
 * @Date:Created in 4:20 PM 7/30/2018
 */
@Configuration
public class QuartzConfiguration {

    @Bean(name = "reptilianJob")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(QuartzTask quartzTask){

        MethodInvokingJobDetailFactoryBean  jobDetail = new MethodInvokingJobDetailFactoryBean ();

        //是否执行并发
        jobDetail.setConcurrent ( false );

        //设置任务的名字
        jobDetail.setName ("reptilianJob" );

        //设置任务的分组，在多任务的时候使用
        jobDetail.setGroup ( "reptilianGroup" );

        //需要执行的对象
        jobDetail.setTargetObject ( quartzTask );

        //执行具体对象的方法
        jobDetail.setTargetMethod ( "reptilian" );

        return  jobDetail;
    }


    /**
     * 定时触发器
     * @param jobDetail
     * @return
     */
    @Bean(name = "JobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(JobDetail  jobDetail){

        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean ();

        tigger.setJobDetail ( jobDetail );
        //cron 表达式 ，每1分钟执行一次
        tigger.setCronExpression ( "0 0/1 * * * ?" );

        tigger.setName ( "reptilianTrigger" );

        return  tigger;
    }


    @Bean(name= "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger){

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean ();

        //用于quartz集群，QuartzScheduler 启动更新已存在的Job
        factoryBean.setOverwriteExistingJobs ( true );

        //延时启动，应用启动1秒后
        factoryBean.setStartupDelay ( 1 );

        //注册触发器
        factoryBean.setTriggers ( trigger );

        return  factoryBean;
    }


}
