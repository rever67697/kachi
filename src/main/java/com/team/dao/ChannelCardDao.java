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
  public List<ChannelCard> queryChannelCardsBy(Map<String, Object> map);

  public ChannelCard queryChannelCardById(String Id);

  public boolean insertChannelCard(ChannelCard channelCard);

  public boolean insertChannelCards(List<ChannelCard> list);

  public boolean deleteChannelCards(String[] list);

  public boolean updateChannelCard(ChannelCard channelCard);
}
