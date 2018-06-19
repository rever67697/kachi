package com.team.dao;


import com.team.model.OperatorPrefer;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:18 2018/4/23
 */
public interface OperatorPreferDao {


    List<OperatorPrefer> list(Map<String, Object> map);

    int insert(OperatorPrefer operatorPrefer);

    int update(OperatorPrefer operatorPrefer);

    int delete(List<Integer> list);
}
