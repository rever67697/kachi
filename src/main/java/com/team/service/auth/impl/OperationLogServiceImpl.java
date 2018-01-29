package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.auth.OperationLogDao;
import com.team.model.auth.OperationLog;
import com.team.service.auth.OperationLogService;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-29下午5:03:00
 * author:wuzhiheng
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogDao  operationLogDao;
	
	@Override
	public ResultList getLogList(String username,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<OperationLog> list = operationLogDao.getLogList(username);
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

}
