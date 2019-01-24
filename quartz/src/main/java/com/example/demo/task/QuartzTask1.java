package com.example.demo.task;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:46 PM 1/24/2019
 */
@Service
public class QuartzTask1 {
    public  void pring(){
        System.out.println("执行业务处理task1：" + new Date());
    }
}
