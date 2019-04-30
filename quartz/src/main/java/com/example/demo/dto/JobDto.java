package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:12 PM 4/30/2019
 */
@Data
public class JobDto {
    /**
     * 定时任务全类名
     */
    @NotBlank(message = "类名不能为空")
    private String jobClassName;
    /**
     * 名字
     */
    @NotBlank(message = "任务(触发器 名)名不能为空")
    private String name;

    /**
     * 组名
     */
    @NotBlank(message = "任务组（触发器）名不能为空")
    private String GroupName;
    /**
     * 定时任务cron表达式
     */

    private String cronExpression;

    /**
     * 重复间隔
     */
    private Long repeatInterval;
    /**
     * 触发次数
     */
    private Long timesTriggered;
    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * 定时任务状态
     */
    private String triggerState;
}
