package com.team.jobs;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * @Author : wuzhiheng
 * @Description : 定时作业一些管理类
 * @Date Created in 15:50 2019-02-26
 */
public class JobManager {

//    public static String JOB_GROUP_NAME = "kachi-job";

//    public static String TRIGGER_GROUP_NAME = "kachi-trigger";

    private static JobManager jobManager = new JobManager();

    private JobManager(){};

    public static JobManager me(){
        return jobManager;
    }

    //这个的话要注意人物类里面的bean的注入
    public JobDetail buildJobDetail(Class<? extends Job> task, String jobName){
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName(jobName);
//        jobDetail.setGroup(JOB_GROUP_NAME);
        jobDetail.setJobClass(task);
        return jobDetail;
    }

    public CronTriggerImpl buildTrigger(String cronExpression,String triggerName){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            trigger.setName(triggerName);
//            trigger.setGroup(TRIGGER_GROUP_NAME);

            //关联一个job
//            trigger.setJobName("");
//            trigger.setJobGroup("");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trigger;
    }

    public CronTriggerImpl buildTrigger(String cronExpression, String triggerName, JobKey jobKey){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            trigger.setName(triggerName);
//            trigger.setGroup(TRIGGER_GROUP_NAME);

            //关联一个job
            trigger.setJobKey(jobKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trigger;
    }

}
