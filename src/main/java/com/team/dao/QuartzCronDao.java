package com.team.dao;

import com.team.model.QuartzCron;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午11:15 2018/7/2
 */
public interface QuartzCronDao {

    QuartzCron get();

    int update(QuartzCron quartzCron);

}
