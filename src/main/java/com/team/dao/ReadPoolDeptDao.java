package com.team.dao;

import com.team.model.ReadPoolDept;

/**
 * 创建日期：2017-12-26下午9:09:38
 * author:wuzhiheng
 */
public interface ReadPoolDeptDao {

	/**
	 * 插入预分配卡池一条数据
	 *@param readPoolDept
	 *@return
	 *return
	 */
	public int saveReadPoolDept(ReadPoolDept readPoolDept);
	
}
