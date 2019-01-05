package com.qinxc.basic;

import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qxc
 * @date 2018/11/28.
 */
public class MyJob implements org.quartz.Job {

    public MyJob() {
    }

    public void execute(JobExecutionContext context) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println("Hello World!  MyJob is executing. " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

        MyHandler handler = (MyHandler) context.getJobDetail().getJobDataMap().get("storeHandler");
        System.out.println(handler.toString());
    }
}
