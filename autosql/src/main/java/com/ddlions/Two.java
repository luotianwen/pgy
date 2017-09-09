package com.ddlions;

import com.ddlions.two.TwoJob;
import com.ddlions.util.ReadPro;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/6/12
 *          Time: 0:15
 * @author: mm
 * @since 3.0
 */
public class Two {
    public void go() throws Exception {
        // 首先，必需要取得一个Scheduler的引用
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        //jobs可以在scheduled的sched.start()方法前被调用

        //job 1将每隔20秒执行一次
        JobDetail job = newJob(TwoJob.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule(ReadPro.getValue("cron"))).build();
        Date ft = sched.scheduleJob(job, trigger);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());


        sched.start();

    }

    public static void main(String[] args) throws Exception {

        Two test = new Two();
        test.go();

    }


}
