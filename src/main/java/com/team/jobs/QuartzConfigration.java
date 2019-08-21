package com.team.jobs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfigration {

    /**
     * attention:
     * Details：配置定时任务
     */
    @Bean(name = "jobDetail-handleProblemcard")
    public MethodInvokingJobDetailFactoryBean handleProblemcardTask(HandleProblemcardTask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);

        jobDetail.setName("job-handleProblemcard");// 设置任务的名字
//        jobDetail.setGroup(JobManager.JOB_GROUP_NAME);// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * run为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的run方法
         */
        jobDetail.setTargetMethod("run");
        
        return jobDetail;
    }

    @Bean(name = "jobDetail-sendMsg")
    public MethodInvokingJobDetailFactoryBean sendMsg(SendMessageTask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);

        jobDetail.setName("job-sendMsg");// 设置任务的名字
//        jobDetail.setGroup(JobManager.JOB_GROUP_NAME);// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * run为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的run方法
         */
        jobDetail.setTargetMethod("run");
        return jobDetail;
    }

    @Bean(name = "jobDetail-statTerminal")
    public MethodInvokingJobDetailFactoryBean statTerminal(StatTerminalTask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);

        jobDetail.setName("job-statTerminal");// 设置任务的名字
//        jobDetail.setGroup(JobManager.JOB_GROUP_NAME);// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * run为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的run方法
         */
        jobDetail.setTargetMethod("run");
        return jobDetail;
    }

//    /**
//     * attention:
//     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
//     */
//    @Bean(name = "jobTrigger")
//    public CronTriggerFactoryBean cronJobTrigger(@Qualifier("jobDetail") MethodInvokingJobDetailFactoryBean jobDetail) {
//        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
//        tigger.setJobDetail(jobDetail.getObject());
//
//        QuartzCron quartzCron = quartzCronDao.get();
//
//        if (quartzCron.getStatus()==0){
//            tigger.setCronExpression(quartzCron.getCronStr());// 初始时的cron表达式
//        }else {
//            tigger.setCronExpression("0 0 0 1 1 ? 2050");// 初始时的cron表达式
//        }
//        tigger.setName("srd-chhliu");// trigger的name
//        return tigger;
//
//    }

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
//        bean.setStartupDelay(1);
        // 注册触发器
//        bean.setTriggers(cronJobTrigger);

        return bean;
    }

}