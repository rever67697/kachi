package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.SimCard;
import com.team.model.SimPool;
import com.team.vo.OutlineInfo;

/**
 * 卡表的相关操作	m_simcard
 * 卡的状态有5种（0：正常；1:停用; 2：指定;3:待激活；4：作废）只有正常和指定状态的卡才会被终端选中使用
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface SimCardDao {
	
	/**
	 * 根据卡池id查找其下面的卡，为了在页面显示什么位置有卡，卡的状态是什么
	 *@param cpId
	 *@return
	 *return
	 */
	public List<SimCard> getSimCardByPool(@Param("cpId")Integer cpId);
	
	/**
	 * 根据卡套餐id寻找是否有SIM卡在使用这个套餐，如果没有数据返回，则判断没有sim卡在使用这个套餐
	 *@param packageId
	 *@return
	 *return
	 */
	public String getPackageExist(@Param("packageId")Integer packageId);
	
	/**
	 * 状态为作废的卡可以删除
	 *@param list
	 *@return
	 *return
	 */
	public int deleteSimCard(List<Integer> list);
	
	/**
	 * 根据条件寻找出sim卡列表
	 *@param map
	 *@return
	 *return
	 */
	public List<SimCard> getSimCard(Map<String, Object> map);
	
	/**
	 * 查询流量卡总览信息
	 *@param map
	 *@return
	 *return
	 */
	public List<OutlineInfo> getOutlineInfo(@Param("departmentId")Integer departmentId);
	
	/**
	 * 根据卡池id更新卡所属的代理商
	 *@param simPool
	 *@return
	 *return
	 */
	public int updateCardDept(SimPool simPool);
}
