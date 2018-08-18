package com.team.dao;

import com.team.vo.stat.StatBean;
import com.team.vo.stat.TerminalCost;
import com.team.vo.stat.TerminalCount;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午9:23 2018/8/16
 */
public interface StatDao {

    /**查询每天激活终端**/
    List<TerminalCount> queryTerminalCount();

    /**查询当月终端消耗排名前十名**/
    List<TerminalCost> queryTerminalCost();

    /**查询当月激活终端**/
    StatBean stat();


}
