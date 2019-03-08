package com.team.service;

import com.team.model.SimPackage;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23下午6:06:15
 * author:wuzhiheng
 */
public interface SimPackageService {

	ResultList list(Integer departmentId,Integer status,String name,int page,int rows);
	
	ReturnMsg delete(Integer id);
	
	ReturnMsg save(SimPackage simPackage);
	
}
