package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.SimGroup;

/**
 * 创建日期：2018-2-9下午4:05:38
 * author:wuzhiheng
 */
public interface SimGroupDao {

	List<SimGroup> getSimGroupByImsi(@Param("imsi") Long imsi);
	
	int delete(Map<String, Object> map);
	
	int insert(SimGroup simGroup);
}
