package com.team.jobs;

import com.team.dao.StatDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description : 定时任务，统计在线用户数
 * @Date Created in 下午5:21 2018/7/2
 */
@Component
public class StatTerminalTask {

    private Logger logger = Logger.getLogger(StatTerminalTask.class);

    @Autowired
    private StatDao statDao;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void run() throws Exception {

        statDao.statTerminal();

        logger.info("现在时间:" + sdf.format(new Date()) + " 执行处理完统计在线终端任务!");
    }




}
