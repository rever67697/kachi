package com.team.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    int count = 0;
    if (ids != null) {
      String[] idArr = ids.split(",");
      count = readyTerminalSimDao.delete(idArr);
    }
    return count > 0 ? IConstant.MSG_OPERATE_SUCCESS : IConstant.MSG_OPERATE_ERROR;
  }


  @Override
  public ReturnMsg saveReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
    int count = 0;
    if (readyTerminalSim.getId() != null) {
      count = readyTerminalSimDao.update(readyTerminalSim);
    } else {
      readyTerminalSim.setInsertDate(new Date(System.currentTimeMillis()));
      count = readyTerminalSimDao.insert(readyTerminalSim);
    }
    return count > 0 ? IConstant.MSG_OPERATE_SUCCESS : IConstant.MSG_OPERATE_ERROR;
  }

}
