package com.example.demo.service;

import com.example.demo.dto.JobDto;
import org.quartz.SchedulerException;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:03 PM 4/30/2019
 */
public interface JobService {

    /**
     * 添加并且启动任务
     * @param jobDto 参数 {@link JobDto}
     */
    void addJob(JobDto jobDto) throws Exception;

    /**
     * 删除定时任务
     * @param jobDto
     */
    void deleteJob(JobDto jobDto) throws SchedulerException;


    /**
     * 暂定定时任务
     * @param jobDto
     */
    void pauseJob(JobDto jobDto) throws SchedulerException;


    /**
     * 恢复定时任务
     * @param jobDto
     */
    void resumeJob(JobDto jobDto) throws SchedulerException;

    /**
     * 修改定时任务
     * @param jobDto
     */
    void update(JobDto jobDto) throws SchedulerException;

    /**
     * 启动所有的任务
     */
    void startAllJobs();

    /**
     * 关闭所有的任务
     */
    void shutdownAllJobs();
}

