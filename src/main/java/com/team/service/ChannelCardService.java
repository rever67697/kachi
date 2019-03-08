package com.team.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.team.model.ChannelCard;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ChannelCardService {

	 ResultList getChannelCardList(Integer tsid, Long imsi, Integer departmentId, int page, int rows);

	 ReturnMsg deleteChannelCards(String ids);

	 ReturnMsg saveChannelCard(ChannelCard channelCard);

	 ReturnMsg getChannelCardList(MultipartFile file);

	 void insertBatch(List<ChannelCard> list);

}
