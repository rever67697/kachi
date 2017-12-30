package com.team.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@RestController
public class ReadyTernimalSimController {
  @Autowired
  private ReadyTerminalSimService ReadyTerminalSimService;

  @PostMapping("getReadyTerminalSim")
  public ResultList getReadyTerminalSim(String tsid, String imsi, int page, int rows) {
    return ReadyTerminalSimService.getReadyTerminalSim(tsid, imsi, page, rows);
  }

  @PostMapping("saveReadyTerminalSim")
  public ReturnMsg saveReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
    return ReadyTerminalSimService.saveReadyTerminalSim(readyTerminalSim);
  }
  @PostMapping("deleteReadyTerminalSim")
  public ReturnMsg deleteReadyTerminalSim(@Param(value = "ids") String ids) {
    return ReadyTerminalSimService.deleteReadyTerminalSim(ids);
  }
}
