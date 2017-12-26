package com.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.TerminalSimDao;
import com.team.model.TerminalSim;
import com.team.service.TerminalSimService;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 终端对应的卡的相关操作	m_terminal_sim
 * 创建日期：2017-12-22下午10:23:43
 * author:wuzhiheng
 */
@Service
public class TerminalSimServiceImpl implements TerminalSimService{
	
	@Autowired
	private TerminalSimDao terminalSimDao;

	@Override
	/**
	 * 通过终端tsid找到对应的SIM卡
	 *@param tsid
	 *@return
	 *return
	 */
	public ReturnMsg getTerminalSimByTsid(Integer tsid) {
		List<TerminalSim> list = terminalSimDao.getTerminalSimByTsid(tsid);
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		if(list != null && list.size() > 0){
			returnMsg.setData(list.get(0));
		}
		return returnMsg;
	}

}
