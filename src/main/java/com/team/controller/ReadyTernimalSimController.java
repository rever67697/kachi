package com.team.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.service.SimCardService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@RestController
public class ReadyTernimalSimController {
  @Autowired
  private ReadyTerminalSimService ReadyTerminalSimService;
  @Autowired
  private SimCardService simCardService;

  @PostMapping("getReadyTerminalSim")
  public ResultList getReadyTerminalSim(String tsid, String imsi, int page, int rows) {
    return ReadyTerminalSimService.getReadyTerminalSim(tsid, imsi, page, rows);
  }

  @PostMapping("saveReadyTerminalSim")
  public ReturnMsg saveReadyTerminalSim(Integer tsid,
      @RequestParam(value = "imsi[]", required = false) String[] imsis,
      @RequestParam(value = "imsi", required = false) String imsi, Integer type) {
    Integer user = 0;//
    if (imsis == null) {
      imsis = new String[] {imsi};
    }
    return ReadyTerminalSimService.saveReadyTerminalSim(tsid, imsis, type, user);
  }

  @PostMapping("updateReadyTerminalSim")
  public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
    return ReadyTerminalSimService.updateReadyTerminalSim(readyTerminalSim);
  }
  @PostMapping("deleteReadyTerminalSim")
  public ReturnMsg deleteReadyTerminalSim(@Param(value = "ids") String ids) {
    return ReadyTerminalSimService.deleteReadyTerminalSim(ids);
  }

  @PostMapping("/getSimCardsInAppointCard")
  public ResultList getSimCard(@RequestParam(value = "q") String number, int page, int rows) {
    return simCardService.getSimCard(null, null, number, null, page, rows);
  }
}
