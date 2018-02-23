package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.SimGroupDao;
import com.team.model.SimGroup;
import com.team.service.SimGroupService;

/**
 * 创建日期：2018-2-9下午4:09:05
 * author:wuzhiheng
 */
@Service
public class SimGroupServiceImpl implements SimGroupService{

	@Autowired
	private SimGroupDao simGroupDao;
	
	@Override
	public SimGroup getSimGroup(Long imsi) {
		List<SimGroup> list = simGroupDao.getSimGroupByImsi(imsi);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delete(String groupKey, Long imsi) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("groupKey", groupKey);
		map.put("imsi", imsi);
		return simGroupDao.delete(map);
	}

	@Override
	public int insert(SimGroup simGroup) {
		return simGroupDao.insert(simGroup);
	}

}
