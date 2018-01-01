package com.team.service;

import com.team.model.ReadyTerminalSim;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

public interface ReadyTerminalSimService {
  public ResultList getReadyTerminalSim(String tsid,String imsi,int page,int rows);

  public ReturnMsg deleteReadyTerminalSim(String ids);

  // public ReturnMsg saveReadyTerminalSim(String tsid, String[] imsi, String type, String user);

  public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim);

  public ReturnMsg saveReadyTerminalSim(Integer tsid, String[] imsis, Integer type, Integer user);
}
