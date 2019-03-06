package com.team.service;

import com.team.model.ReadyTerminalSim;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ReadyTerminalSimService {
	
  ResultList list(Integer tsid,Long imsi,Integer dId,Integer departmentId,int page,int rows);

  ReturnMsg delete(ReadyTerminalSim readyTerminalSim);

  ReturnMsg update(ReadyTerminalSim readyTerminalSim);

  ReturnMsg save(Integer tsid,Integer type,String args,Integer userId,String remark);
  
}
