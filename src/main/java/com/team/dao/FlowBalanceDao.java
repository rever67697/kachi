package com.team.dao;

import com.team.model.FlowBalance;
import org.apache.ibatis.annotations.Param;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午4:26 2018/3/26
 */
public interface FlowBalanceDao {

    int save(FlowBalance flowBalance);

    int update(FlowBalance flowBalance);

    /**
     * 根据tsid找出是否存在记录
     **/
    int count(@Param("tsid") Integer tsid);

    FlowBalance findByTsid(@Param("tsid") Integer tsid);

}
