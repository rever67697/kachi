package com.team.service;

import com.team.model.ReadyTerminalSim;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ReadyTerminalSimService {
	
  public ResultList list(Integer tsid,Long imsi,Integer dId,int page,int rows);

  public ReturnMsg delete(ReadyTerminalSim readyTerminalSim);

  public ReturnMsg update(ReadyTerminalSim readyTerminalSim);

  public ReturnMsg save(Integer tsid,Integer type,String args,Integer userId);
  
}
