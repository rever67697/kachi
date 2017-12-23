package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.SimPackage;

/**
 * 卡套餐的相关操作	m_simpackage
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface SimPackageDao {
	
	/**
	 * 查找卡套餐信息
	 *@param map
	 *@return
	 *return
	 */
	public List<SimPackage> getSimPackage(Map<String, Object> map);
	
	/**
	 * 单条删除卡套餐
	 *@param id
	 *@return
	 *return
	 */
	public int deletePackage(@Param("id") Integer id);
	
	/**
	 * 更新套餐信息
	 *@param simPackage
	 *@return
	 *return
	 */
	public int updatePackage(SimPackage simPackage);
	
	/**
	 * 添加卡套餐
	 *@param simPackage
	 *@return
	 *return
	 */
	public int insertPackage(SimPackage simPackage);
	
}
