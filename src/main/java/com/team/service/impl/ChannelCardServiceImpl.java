package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.ChannelCardDao;
import com.team.model.ChannelCard;
import com.team.model.ResultList;
import com.team.service.ChannelCardService;

@Service
public class ChannelCardServiceImpl implements ChannelCardService {
  @Autowired
  private ChannelCardDao channelCardDao;
  @Override
  public ResultList getChannelCards(String number, String operatorCode, String status, int page,
      int rows) {
    PageHelper.startPage(page, rows);
    Map<String, Object> map = new HashMap<>();
    map.put("number", number);
    map.put("operatorCode", operatorCode);
    map.put("status", status);
    List<ChannelCard> list = channelCardDao.queryChannelCardsBy(map);
    PageInfo<ChannelCard> pageInfo = new PageInfo<>(list);
    return new ResultList(pageInfo.getTotal(), list);
  }

  @Override
  public ChannelCard getChannelCard(String Id) {
    return channelCardDao.queryChannelCardById(Id);
  }

  @Override
  public boolean deleteChannelCards(String[] ids) {

    return channelCardDao.deleteChannelCards(ids);
  }

  @Override
  public boolean updateChannelCard(ChannelCard channelCard) {
    return channelCardDao.updateChannelCard(channelCard);
  }

  @Override
  public boolean insertChannelCards(List<ChannelCard> list) {
    return channelCardDao.insertChannelCards(list);
  }

  @Override
  public ChannelCard insertChannelCard(ChannelCard channelCard) {
    channelCardDao.insertChannelCard(channelCard);
    return channelCard;
  }

}
