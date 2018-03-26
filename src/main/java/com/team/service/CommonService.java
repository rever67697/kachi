package com.team.service;

import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23上午1:55:01
 * author:wuzhiheng
 */
public interface CommonService {

	public ReturnMsg getCountryDic();
	
	public ReturnMsg getDepartmentDic(Integer dId);
	
	public ReturnMsg getOperatorDic(Integer countryCode);
	
	public ReturnMsg getSimPoolDic(Integer dId);

	public ReturnMsg getPackageDic(Integer dId);

	public ReturnMsg getProvinceDic(Integer countryCode);

}
