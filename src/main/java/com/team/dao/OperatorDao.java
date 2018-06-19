package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.Operator;

/**
 * 创建日期：2018-2-9下午3:53:42
 * author:wuzhiheng
 */
public interface OperatorDao {

	List<Operator> list(Map<String,Object> map);

	List<Operator> getOperatorByCode(@Param("operatorCode")Integer operatorCode);

	Operator getOperatorById(@Param("id")Integer id);

	int updateLevel(Operator operator);
}
