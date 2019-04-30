package com.example.demo.task;

import org.quartz.*;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:46 PM 1/24/2019
 */
@Component
/**
 禁止并发执行多个相同定义的JobDetail （并不是代表不能并发执行多个不同的job）:
 使用原因：quartz定时任务默认都是并发执行的，不会等到上一次任务执行完毕只会等到间隔时间就会执行。
*/
@DisallowConcurrentExecution
//表示正常执行完job后，jobDataMap的数据应该被改动，以被下一次调用
@PersistJobDataAfterExecution
public class QuartzTask1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行业务处理task1：" + new Date());
    }
}
