package com.team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.Operator;

/**
 * 创建日期：2018-2-9下午3:53:42
 * author:wuzhiheng
 */
public interface OperatorDao {

	List<Operator> getOperatorByCode(@Param("operatorCode")Integer operatorCode);
}
