package com.team.service;

import java.util.List;

import com.team.model.ChannelCard;
import com.team.model.ResultList;

public interface ChannelCardService {
  public ResultList getChannelCards(String number, String operatorCode, String status, int page,
      int rows);

  public ChannelCard getChannelCard(String Id);

  public boolean deleteChannelCards(String[] ids);

  public boolean updateChannelCard(ChannelCard channelCard);

  public boolean insertChannelCards(List<ChannelCard> list);

  public ChannelCard insertChannelCard(ChannelCard channelCard);
}
