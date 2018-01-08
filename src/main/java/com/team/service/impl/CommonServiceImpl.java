package com.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.CommonDao;
import com.team.service.CommonService;
import com.team.util.IConstant;
import com.team.vo.Dictionary;
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
		List<Dictionary> list =commonDao.getCountryDic();
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
		List<Dictionary> list =commonDao.getDepartmentDic();
		if(list != null && list.size() > 0){
			returnMsg.setData(list);
		}
		return returnMsg;
	}

	@Override
	/**
	 * 查找运营商
	 */
	public ReturnMsg getOperatorDic(Integer countryCode) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<Dictionary> list =commonDao.getOperatorDic(countryCode);
		if(list != null && list.size() > 0){
			returnMsg.setData(list);
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg getSimPoolDic(Integer departmentId) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<Dictionary> list =commonDao.getSimPoolDic(departmentId);
		if(list != null && list.size() > 0){
			returnMsg.setData(list);
		}
		return returnMsg;
	}

}
