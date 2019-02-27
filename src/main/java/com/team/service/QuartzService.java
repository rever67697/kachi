package com.team.service;

import javax.annotation.Resource;

import com.team.dao.QuartzCronDao;
import com.team.jobs.HandleProblemcardTask;
import com.team.jobs.JobManager;
import com.team.jobs.SendMessageTask;
import com.team.model.QuartzCron;
import com.team.service.impl.BaseService;
import com.team.vo.ReturnMsg;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuartzService extends BaseService {

    @Resource(name = "jobDetail-handleProblemcard")
    private JobDetail jobDetailhandleProblemcard;

    @Resource(name = "jobDetail-sendMsg")
    private JobDetail jobDetailSendMsg;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Autowired
    private QuartzCronDao quartzCronDao;

    public ReturnMsg scheduleUpdate(QuartzCron quartzCron) throws SchedulerException {

        quartzCron.setProblemcardCronstr("0 */" + quartzCron.getProblemcardMinute() + " * * * ?");
        quartzCron.setMsgCronstr("0 */" + quartzCron.getMsgMinute() + " * * * ?");

        //调度处理问题
        scheduleProblemcard(quartzCron);
        //调度发短信
        scheduleSendMsg(quartzCron);

        //更新数据库
        quartzCronDao.update(quartzCron);

        return successTip();
    }

    private void scheduleProblemcard(QuartzCron quartzCron) throws SchedulerException {
        //关闭或者开启任务  1-开启
        if (quartzCron.getStatusProblemcard() == 1) {

            Trigger trigger = JobManager.me().buildTrigger(quartzCron.getProblemcardCronstr(), "trigger-handleProblemcard");

            scheduler.pauseJob(jobDetailhandleProblemcard.getKey());
            scheduler.deleteJob(jobDetailhandleProblemcard.getKey());
            scheduler.scheduleJob(jobDetailhandleProblemcard,trigger);
        } else {
            //把当前的任务停掉
            scheduler.pauseJob(jobDetailhandleProblemcard.getKey());
        }
    }

    private void scheduleSendMsg(QuartzCron quartzCron) throws SchedulerException {
        //关闭或者开启任务  1-开启
        if (quartzCron.getStatusMsg() == 1) {

            Trigger trigger = JobManager.me().buildTrigger(quartzCron.getMsgCronstr(), "trigger-sendMsg");
            scheduler.pauseJob(jobDetailSendMsg.getKey());
            scheduler.deleteJob(jobDetailSendMsg.getKey());
            scheduler.scheduleJob(jobDetailSendMsg, trigger);
        } else {
            //把当前的任务停掉
            scheduler.pauseJob(jobDetailSendMsg.getKey());
        }
    }


    public QuartzCron getNow() {
        return quartzCronDao.get();
    }

    /**
     * 初始化定时任务
     *
     * @throws Exception
     */
    public void initJob() throws Exception {
        QuartzCron quartzCron = getNow();

        //定时任务-发送短信
        if (quartzCron.getStatusMsg() == 1) {
            System.out.println("========启动jobDetail-sendMsg============");

            Trigger trigger = JobManager.me().buildTrigger(quartzCron.getMsgCronstr(), "trigger-sendMsg");

            scheduler.scheduleJob(jobDetailSendMsg,trigger);
        }

        //定时任务-扫描处理问题卡
        if (quartzCron.getStatusProblemcard() == 1) {
            System.out.println("========启动jobDetail-handleProblemcard=============");

            Trigger trigger = JobManager.me().buildTrigger(quartzCron.getProblemcardCronstr(), "trigger-handleProblemcard");

            scheduler.scheduleJob(jobDetailhandleProblemcard,trigger);
        }


    }

}