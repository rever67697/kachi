package com.team.service;

import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;


/**
 * 卡表相关操作	m_simcard
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimCardService {
	
	public ResultList getSimCardByPool(Integer cpId);
	
	public ReturnMsg deleteSimCard(String ids);
	
	public ResultList getSimCard(String departmentId,String cpId,String number,String status,int page,int rows);
	
	public ReturnMsg getOutlineInfo(String departmentId);
	
	public ResultList getSimCardInAppointCard(String departmentId,String cpId,String number,int page,int rows);
}
