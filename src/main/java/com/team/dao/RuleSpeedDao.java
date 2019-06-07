package com.team.dao;

import com.team.model.RuleSpeed;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 15:37 2019-06-06
 */
public interface RuleSpeedDao {

    List<RuleSpeed> list(Map<String,Object> map);

    int save(RuleSpeed ruleSpeed);

    int update(RuleSpeed ruleSpeed);

    int delete(Integer id);

}
