package com.team.dao;

import com.team.model.StatTerminal;
import com.team.vo.stat.StatBean;
import com.team.vo.stat.TerminalCost;
import com.team.vo.stat.TerminalCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午9:23 2018/8/16
 */
public interface StatDao {

    /**查询每天激活终端**/
    List<TerminalCount> queryTerminalCount(@Param("dId") Integer dId);

    /**查询当月终端消耗排名前十名**/
    List<TerminalCost> queryTerminalCost(@Param("dId") Integer dId);

    /**查询当月激活终端**/
    StatBean stat(@Param("dId") Integer dId);

    /**查询在线终端**/
    List<StatTerminal> queryOnlineCount(Map<String,Object> map);

    /**保存在线终端的统计结果**/
    int statTerminal();



}
