package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.ProblemCard;
import com.team.model.ReadyTerminalSim;
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
	public List<SimCard> getSimCardList(Map<String, Object> map);
	
	/**
	 * 查询流量卡总览信息
	 *@param map
	 *@return
	 *return
	 */
	public List<OutlineInfo> getOutlineInfo(@Param("dId")Integer dId);
	
	/**
	 * 根据卡池id更新卡所属的代理商
	 *@param simPool
	 *@return
	 *return
	 */
	public int updateCardDept(SimPool simPool);
	
	/**
	 * 用于指定卡操作时，更新状态为指定
	 *@param list
	 *@return
	 *return
	 */
	public int updateCardStatus(List<Integer> list);

	public int update(SimCard simCard);

	public List<Map<String,Object>> getSimCardListMap(Map<String,Object> map);

	/**
	 * 删除指定卡时更新对应卡为指定前的状态
	 * @param readyTerminalSim
	 * @return
	 */
	public int resetStatus(ReadyTerminalSim readyTerminalSim);

	/**
	 * 批量更新simcard
	 * @param map
	 * @return
	 */
	public int batchUpdate(Map<String,Object> map);

	/**
	 * 根据id列表寻找列表
	 * @param list
	 * @return
	 */
	public List<SimCard> getByIds(List<Integer> list);

	/**
	 * 根据卡池编号查找列表
	 * @param cpId
	 * @return
	 */
	public List<SimCard> getByPool(@Param("cpId") Integer cpId);

	/**
	 * 根据imsi获取卡
	 * @param imsi
	 * @return
	 */
	public SimCard getByImsi(@Param("imsi") Long imsi);

	/**
	 * 根据imsi更新statu
	 * @param map
	 * @return
	 */
	public int updateStatusByImsi(Map<String,Object> map);

	public SimCard getById(@Param("id") Integer id);

	public List<SimCard> getByPackage(@Param("packageId") Integer packageId);

    public int updateTempImei(SimCard simCard);

	/**
	 * 找出特定时间的问题卡
	 * @return
	 */
	public List<SimCard> getProblemCard(Map<String,Object> map);

	/**
	 * 找出今天的问题卡
	 * @return
	 */
	public List<ProblemCard> listProblemCard(Map<String,Object> map);

	public int saveProblemCard(ProblemCard problemCard);

	public int deleteProblemCard(@Param("imsi") Long imsi);
}
