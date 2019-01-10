package com.team.service;

import com.team.vo.stat.StatBean;

import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午9:24 2018/8/16
 */
public interface StatService {

    Map<String,Object> terminalCount(Integer dId);

    Map<String,Object> terminalCost(Integer dId);

    StatBean fixInformation(StatBean statBean,Integer dId);

}
