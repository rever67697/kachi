package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.vo.Dictionary;

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
	public List<Dictionary> getCountryDic();
	
	/***
	 * 查找所有的代理商
	 *@return
	 *return
	 */
	public List<Dictionary> getDepartmentDic(@Param("dId") Integer dId);
	
	/**
	 * 查找运营商
	 *@return
	 *return
	 */
	public List<Dictionary> getOperatorDic(@Param("countryCode")Integer countryCode);
	
	/**
	 * 查找卡池
	 *@return
	 *return
	 */
	public List<Dictionary> getSimPoolDic(@Param("dId")Integer dId);

	/**
	 * 查找卡套餐
	 *@return
	 *return
	 */
	public List<Dictionary> getPackageDic(Map<String,Object> map);

	/**
	 * 查找省份
	 *@return
	 *return
	 */
	public List<Dictionary> getProvinceDic(@Param("countryCode") Integer countryCode);


	
	
}
