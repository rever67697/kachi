package com.team.service;

import com.team.model.SimGroup;

/**
 * 创建日期：2018-2-9下午4:07:16
 * author:wuzhiheng
 */
public interface SimGroupService {

	SimGroup getSimGroup(Long imsi);
	
	int delete(String groupKey,Long imsi);
	
	int insert(SimGroup simGroup);
}
