package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 创建日期：2017-12-23上午1:53:48
 * author:wuzhiheng
 */
public interface CommonDao {

	/**
	 * 查找所有的国家代码字典
	 *@return
	 *return
	 */
	public List<Map<String, Object>> getCountryDic();
	
	/***
	 * 查找所有的代理商
	 *@return
	 *return
	 */
	public List<Map<String, Object>> getDepartmentDic();
	
	/**
	 * 查找运营商
	 *@return
	 *return
	 */
	public List<Map<String, Object>> getOperatorDic(@Param("countryCode")Integer countryCode);
	
	
}
