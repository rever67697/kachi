package com.team.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.CommonDao;
import com.team.service.CommonService;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 一些公用的功能
 * 创建日期：2017-12-23上午1:55:56
 * author:wuzhiheng
 */
@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDao commonDao;

	@Override
	/**
	 * 查找所有的国家代码字典
	 */
	public ReturnMsg getCountryDic() {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<Map<String, Object>> list =commonDao.getCountryDic();
		if(list != null && list.size() > 0){
			returnMsg.setData(list);
		}
		return returnMsg;
	}

	@Override
	/**
	 * 查找所有的代理商
	 */
	public ReturnMsg getDepartmentDic() {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<Map<String, Object>> list =commonDao.getDepartmentDic();
		if(list != null && list.size() > 0){
			returnMsg.setData(list);
		}
		return returnMsg;
	}

}
