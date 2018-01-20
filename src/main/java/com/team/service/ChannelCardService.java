package com.team.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.team.model.ChannelCard;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ChannelCardService {
	
  public ResultList getChannelCardList(String number,Integer countryCode,Integer operatorCode, 
		  Integer status, int page,int rows);

  public ReturnMsg deleteChannelCards(String ids);

  public ReturnMsg saveChannelCard(ChannelCard channelCard);

  public ReturnMsg getChannelCardList(MultipartFile file);
  
  public void insertBatch(List<ChannelCard> list);
  
}
