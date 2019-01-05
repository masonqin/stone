package com.qinxc.basic;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.*;

/**
 * @author qxc
 * @date 2018/11/27.
 */
public class MyCronJob {


    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our MyJob class
            JobDetail job = newJob(MyJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            job.getJobDataMap().put("storeHandler", new MyHandler("name1", "1"));

            // Trigger the job to run now, and then repeat every x seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    .build();

//            1.        Seconds
//            2.        Minutes
//            3.        Hours
//            4.        Day-of-Month
//            5.        Month
//            6.        Day-of-Week
//            CronTrigger trigger = newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .withSchedule(cronSchedule("0 15 10 * * ?"))
//                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // and start it off
            scheduler.start();
//            Thread.sleep(90L * 1000L);
//            scheduler.shutdown(true);
            System.out.println("go on running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



