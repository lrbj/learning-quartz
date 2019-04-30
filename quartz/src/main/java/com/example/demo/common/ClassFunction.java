package com.example.demo.common;

import com.example.demo.job.BaseJob;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:34 PM 4/30/2019
 */
public class ClassFunction {
    public static BaseJob getJobClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return (BaseJob) clazz.newInstance();
    }
}
