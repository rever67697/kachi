package com.team.service;


import org.springframework.web.multipart.MultipartFile;

import com.team.model.ChannelCard;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ChannelCardService {
	
  public ResultList getChannelCard(String number, String operatorCode, String status, int page,int rows);

  public ReturnMsg deleteChannelCards(String ids);

  public int updateChannelCard(ChannelCard channelCard);

  public int insertChannelCard(ChannelCard channelCard);
  
  public ReturnMsg getChannelCardList(MultipartFile file);
  
}
