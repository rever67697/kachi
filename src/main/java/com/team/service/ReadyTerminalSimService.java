package com.team.service;

import com.team.model.ReadyTerminalSim;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ReadyTerminalSimService {
	
  public ResultList getReadyTerminalSim(String tsid,String imsi,int page,int rows);

  public ReturnMsg deleteReadyTerminalSim(String ids);

  public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim);

  public ReturnMsg saveReadyTerminalSim(Integer tsid,Integer type,String args);
  
}
