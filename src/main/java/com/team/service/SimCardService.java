package com.team.service;

import com.team.dto.SimCardDTO;
import com.team.model.FlowMonth;
import com.team.model.SimCard;
import com.team.model.SimGroup;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;


/**
 * 卡表相关操作	m_simcard
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimCardService {
	
	ReturnMsg getSimCardByPool(Integer cpId);
	
	ReturnMsg deleteSimCard(String ids);
	
	ResultList getSimCardList(SimCardDTO simCard,int page, int rows);

	File getCsv(SimCardDTO simCard) throws Exception;
	
	ReturnMsg getOutlineInfo();

	ReturnMsg update(SimCard simCard);

//	SimGroup initGroupSim2Cache(SimCard simCard);

	ReturnMsg batchUpdate(SimCardDTO simCard, String ids);

	boolean updateGroupSim2Cache(SimCard simCard,int status);

	FlowMonth getNowFlowMonth(SimCard simCard);

    ReturnMsg getSimcardList(MultipartFile file);

	void batchUpdateTempImei(List<SimCard> list);

	boolean updateSimCardFromCache(SimCard simCard,boolean onlySimCardCache);

}
