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
import org.springframework.util.CollectionUtils;

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
	 * 终端管理 -> 查找占用卡
	 *@param tsid
	 *@return
	 */
	public ReturnMsg getTerminalSimByTsid(Integer tsid) {
		List<TerminalSim> list = terminalSimDao.getTerminalSimByTsid(tsid);
		ReturnMsg returnMsg = super.successTip();
		if(list != null && list.size() > 0){
			returnMsg.setData(list.get(0));
		}
		return returnMsg;
	}

	/**
	 * 在线终端 -> 列表
	 * @return
	 */
	@Override
	public ResultList list(Integer departmentId,Integer tsid, Long imsi, int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tsid", tsid);
		map.put("imsi", imsi);
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.getDId());
		List<TerminalSim> list = terminalSimDao.getTerminalSimList(map);
		PageInfo<TerminalSim> pageInfo = new PageInfo<TerminalSim>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	/**
	 * 在线终端 -> 终端换卡
	 * @param tsid
	 * @return
	 */
	@Override
	public ReturnMsg changeCard(Integer tsid) {
		TerminalSim terminalSim  = terminalSimDao.getByTsid(tsid);
		terminalSimDao.deleteTerminalByTsid(tsid);

		//释放simcard
		if(terminalSim!=null){
			simCardService.updateGroupSim2Cache(simCardDao.getByImsi(terminalSim.getImsi()),0);
		}

		return super.successTip();
	}

}
