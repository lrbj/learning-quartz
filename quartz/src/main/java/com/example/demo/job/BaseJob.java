package com.example.demo.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.stereotype.Component;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:30 PM 4/30/2019
 */
@Component
/**
 禁止并发执行多个相同定义的JobDetail （并不是代表不能并发执行多个不同的job）:
 使用原因：quartz定时任务默认都是并发执行的，不会等到上一次任务执行完毕只会等到间隔时间就会执行。
 //*/
@DisallowConcurrentExecution
//表示正常执行完job后，jobDataMap的数据应该被改动，以被下一次调用
@PersistJobDataAfterExecution
public interface BaseJob extends Job {
}
