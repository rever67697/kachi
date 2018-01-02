package com.team.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.ReadyTerminalSimDao;
import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;


@Service
public class ReadyTerminalSimServiceImpl implements ReadyTerminalSimService {
  @Autowired
  private ReadyTerminalSimDao readyTerminalSimDao;

  @Override
  public ResultList getReadyTerminalSim(String tsid, String imsi, int page, int rows) {
    PageHelper.startPage(page, rows);
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("tsid", "%" + tsid + "%");
    paramMap.put("imsi", "%" + imsi + "%");
    List<ReadyTerminalSim> list = readyTerminalSimDao.getReadyTerminalSim(paramMap);
    PageInfo<ReadyTerminalSim> pageInfo = new PageInfo<ReadyTerminalSim>(list);
    return new ResultList(pageInfo.getTotal(), list);
  }

  @Override
  public ReturnMsg deleteReadyTerminalSim(String ids) {
    String[] arr = ids.split(",");
	List<Integer> list = new ArrayList<Integer>();
	for (String string : arr) {
		list.add(Integer.valueOf(string));
	}
	int  count = readyTerminalSimDao.delete(list);
    return count > 0 ? IConstant.MSG_OPERATE_SUCCESS : IConstant.MSG_OPERATE_ERROR;
  }

  @Transactional
  @Override
  public ReturnMsg saveReadyTerminalSim(Integer tsid, String[] imsis, Integer type, Integer user) {
    List<ReadyTerminalSim> list = new ArrayList<ReadyTerminalSim>();
    List<Long> imsiList = new ArrayList<Long>();
    int count = 0;
    ReadyTerminalSim readyTerminalSim = new ReadyTerminalSim(tsid, type, user);
    readyTerminalSim.setInsertDate(new Date(System.currentTimeMillis()));
    for (int i = 0; i < imsis.length; i++) {
      if (!org.springframework.util.StringUtils.isEmpty(imsis[i])) {
        readyTerminalSim.setImsi(Long.parseLong(imsis[i]));
        imsiList.add(Long.parseLong(imsis[i]));
        list.add(readyTerminalSim);
      }
    }
    count = readyTerminalSimDao.insert(list);
    count = readyTerminalSimDao.changeCardStatus(imsiList);
    return count > 0 ? IConstant.MSG_OPERATE_SUCCESS : IConstant.MSG_OPERATE_ERROR;
  }

  @Override
  public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
    int count = 0;
    if (readyTerminalSim.getId() != null) {
      count = readyTerminalSimDao.update(readyTerminalSim);
    }
    return count > 0 ? IConstant.MSG_OPERATE_SUCCESS : IConstant.MSG_OPERATE_ERROR;
  }


}
