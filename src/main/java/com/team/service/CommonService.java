package com.team.service;

import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23上午1:55:01
 * author:wuzhiheng
 */
public interface CommonService {

	ReturnMsg getCountryDic();
	
	ReturnMsg getDepartmentDic();
	
	ReturnMsg getOperatorDic(Integer countryCode,Integer mcc);
	
	ReturnMsg getSimPoolDic();

	ReturnMsg getPackageDic(Integer operatorCode);

	ReturnMsg getProvinceDic(Integer countryCode);

	ReturnMsg getMccDic();

	Object q(Long imsi);

}
