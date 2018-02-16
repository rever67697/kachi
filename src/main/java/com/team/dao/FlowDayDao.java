package com.team.dao;

import com.team.model.FlowDay;

import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午1:42 2018/2/16
 */
public interface FlowDayDao {

    /**
     * 获取卡当月共使用了多少流量
     * @param map
     * @return
     */
    public FlowDay getUsedFlow(Map<String,Object> map);

}
