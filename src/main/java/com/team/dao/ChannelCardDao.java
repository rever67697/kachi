package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.ChannelCard;

/**
 * @author Wzq
 *副卡表操作
 * 2017年12月21日 下午11:00:49
 *
 */
public interface ChannelCardDao {
	
  /**
   * 查询副卡列表	
   *@param map
   *@return
   *return
   */
  public List<ChannelCard> getChannelCard(Map<String, Object> map);
  
  /**
   * 插入单条副卡
   *@param channelCard
   *@return
   *return
   */
  public int insertChannelCard(ChannelCard channelCard);
  /**
   * 批量删除副卡，状态设置为1
   *@param list
   *@return
   *return
   */
  public int deleteChannelCards(List<Integer> ids);
  
  /**
   * 更新副卡信息
   *@param channelCard
   *@return
   *return
   */
  public int updateChannelCard(ChannelCard channelCard);
  
}
