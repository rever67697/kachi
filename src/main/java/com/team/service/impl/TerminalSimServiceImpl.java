package com.team.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.dao.SimCardDao;
import com.team.model.SimCard;
import com.team.service.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalSimDao;
import com.team.model.TerminalSim;
import com.team.service.TerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 终端对应的卡的相关操作	m_terminal_sim
 * 创建日期：2017-12-22下午10:23:43
 * author:wuzhiheng
 */
@Transactional
@Service
public class TerminalSimServiceImpl extends BaseService implements TerminalSimService{
	
	@Autowired
	private TerminalSimDao terminalSimDao;
	@Autowired
	private SimCardService simCardService;
	@Autowired
	private SimCardDao simCardDao;

	@Override
	/**
	 * 通过终端tsid找到对应的SIM卡
	 *@param tsid
	 *@return
	 *return
	 */
	public ReturnMsg getTerminalSimByTsid(Integer tsid) {
		List<TerminalSim> list = terminalSimDao.getTerminalSimByTsid(tsid);
		ReturnMsg returnMsg = super.successTip();
		if(list != null && list.size() > 0){
			returnMsg.setData(list.get(0));
		}
		return returnMsg;
	}

	@Override
	public ResultList getTerminalSimList(Integer departmentId,Integer dId,Integer tsid, Long imsi, int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tsid", tsid);
		map.put("imsi", imsi);
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		List<TerminalSim> list = terminalSimDao.getTerminalSimList(map);
		PageInfo<TerminalSim> pageInfo = new PageInfo<TerminalSim>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	public ReturnMsg deleteTerminalByIds(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		List<TerminalSim> terminalSimList  = terminalSimDao.getByIds(list);
		int count = terminalSimDao.deleteTerminalByIds(list);

		//释放simcard
		for (TerminalSim terminalSim : terminalSimList) {
			simCardService.updateGroupSim2Cache(simCardDao.getByImsi(terminalSim.getImsi()),0);
		}


		return count>0?super.successTip():super.errorTip();
	}

}
