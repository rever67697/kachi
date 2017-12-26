package com.team.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.ReadPoolDeptDao;
import com.team.model.ReadPoolDept;
import com.team.service.ReadPoolDeptService;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-26下午9:12:06
 * author:wuzhiheng
 */
@Service
public class ReadPoolDeptServiceImpl implements ReadPoolDeptService{

	@Autowired
	private ReadPoolDeptDao readPoolDeptDao;

	@Override
	public ReturnMsg saveReadPoolDept(ReadPoolDept readPoolDept) {
		int count = readPoolDeptDao.saveReadPoolDept(readPoolDept);
		return count>0?IConstant.MSG_OPERATE_SUCCESS:IConstant.MSG_OPERATE_ERROR;
	}
}
