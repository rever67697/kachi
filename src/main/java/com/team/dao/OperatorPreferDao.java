package com.team.dao;


import com.team.model.OperatorPrefer;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:18 2018/4/23
 */
public interface OperatorPreferDao {


    List<OperatorPrefer> list();

    int insert(OperatorPrefer operatorPrefer);

    int update(OperatorPrefer operatorPrefer);

    int delete(List<Integer> list);
}
