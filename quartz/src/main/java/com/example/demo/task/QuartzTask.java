package com.example.demo.task;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Kayla,Ye
 * @Description:
 * @Date:Created in 4:27 PM 7/30/2018
 */
@Service
public class QuartzTask {

     public  void reptilian(){
         System.out.println ( "执行业务处理逻辑：" + new Date () );
     }

}
