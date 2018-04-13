package com.team.service;

import com.team.dto.SimCardDTO;
import com.team.model.SimCard;
import com.team.model.SimGroup;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

import java.io.File;
import java.io.FileWriter;


/**
 * 卡表相关操作	m_simcard
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimCardService {
	
	public ReturnMsg getSimCardByPool(Integer cpId);
	
	public ReturnMsg deleteSimCard(String ids);
	
	public ResultList getSimCardList(SimCardDTO simCard,Integer dId,int page,int rows);

	public File getCsv(SimCard simCard,Integer dId) throws Exception;
	
	public ReturnMsg getOutlineInfo(Integer dId);

	public ReturnMsg update(SimCard simCard);

	public SimGroup initGroupSim2Cache(SimCard simCard);

	public ReturnMsg batchUpdate(SimCardDTO simCard, String ids);

	public boolean updateGroupSim2Cache(SimCard simCard,int status);
	
}
