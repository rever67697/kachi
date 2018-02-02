package com.team.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.ReadyTerminalSimDao;
import com.team.dao.SimCardDao;
import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@Transactional
@Service
public class ReadyTerminalSimServiceImpl extends BaseService implements ReadyTerminalSimService {
	
	@Autowired
	private ReadyTerminalSimDao readyTerminalSimDao;
	@Autowired
	private SimCardDao simCardDao;

	@Override
	public ResultList getReadyTerminalSim(Integer tsid, Long imsi, int page,
			int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("imsi", imsi);
		paramMap.put("tsid", tsid);
		List<ReadyTerminalSim> list = readyTerminalSimDao
				.getReadyTerminalSim(paramMap);
		PageInfo<ReadyTerminalSim> pageInfo = new PageInfo<ReadyTerminalSim>(
				list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	public ReturnMsg deleteReadyTerminalSim(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		int count = readyTerminalSimDao.delete(list);
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public ReturnMsg saveReadyTerminalSim(Integer tsid,Integer type,String args,Integer userId) {
		int count = 0;
		List<ReadyTerminalSim> list = new ArrayList<ReadyTerminalSim>();
		List<Integer> ids = new ArrayList<Integer>();
		for (String string : args.split(";")) {
			String[] params = string.split(",");
			ReadyTerminalSim readyTerminalSim = new ReadyTerminalSim(tsid, CommonUtil.putLong(params[2]),CommonUtil.putInteger(params[1]), type, userId);
			list.add(readyTerminalSim);
			ids.add(CommonUtil.putInteger(params[0]));
		}
		if(list.size()>0){
			//插入数据
			count = readyTerminalSimDao.insert(list);
			//更新卡表卡状态为指定
			simCardDao.updateCardStatus(ids);
		}
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
		int count = 0;
		if (readyTerminalSim.getId() != null) {
			count = readyTerminalSimDao.update(readyTerminalSim);
		}
		return count>0?super.successTip():super.errorTip();
	}

}
