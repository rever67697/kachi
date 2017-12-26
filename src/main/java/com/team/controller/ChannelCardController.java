package com.team.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.ChannelCard;
import com.team.model.ResultList;
import com.team.service.ChannelCardService;

/**
 * @author Wzq
 *
 * 2017年12月21日 下午8:33:47
 *
 */
@RestController
public class ChannelCardController {
  @Autowired
  private ChannelCardService channelCardService;

  /**
   * 查询获取副卡列表
   * 
   * @param number 卡号
   * @param operatorCode 运营商编号
   * @param status 状态
   * @param page 页数
   * @param rows 一页的记录数
   * @return
   */
@RequestMapping("getChannelCards")
  public ResultList getChannelCards(String number, String operatorCode, String status, int page,
      int rows) {
    return channelCardService.getChannelCards(number, operatorCode, status, page, rows);
}

  /**
   * 根据副卡id获取副卡信息
   * 
   * @param id
   * @return
   */
  @GetMapping("getChannelCard")
  public ChannelCard getChannelCard(String id) {
    return channelCardService.getChannelCard(id);
  }

  /**
   * 单个插入副卡
   * 
   * @param channelCard
   * @return
   */
  @PostMapping("insertChannelCard")
  public Map<String, String> insertChannelCard(ChannelCard channelCard) {
    Map<String, String> map = new HashMap<>();
    if (channelCardService.insertChannelCard(channelCard).getId() != null) {
      map.put("msg", "增加成功");
    }
    return map;
  }

  /**
   * 批量插入副卡信息
   * 
   * @return
   */
  @PostMapping("insertChannelCards")
  public boolean insertChannelCards() {
    List<ChannelCard> list = new ArrayList<>();
    return channelCardService.insertChannelCards(list);
  }

  @PostMapping("updateChannelCard")
  public Map<String, String> updateChannelCard(ChannelCard channelCard) {
    Map<String, String> map = new HashMap<>();
    if (channelCardService.updateChannelCard(channelCard)) {
      map.put("msg", "修改成功");
    }
    return map;
  }
  /**
   * 批量删除副卡信息
   * 
   * @return
   */
  @RequestMapping("deleteChannelCards")
  public Map<String, String> deletaChannelCards(String idsStr) {
    Map<String, String> resultMap = new HashMap<>();
    System.out.println(idsStr);
    String[] ids = new String[] {};
    if (idsStr != null) {
      ids = idsStr.split(",");
    }
    if (channelCardService.deleteChannelCards(ids)) {
      resultMap.put("msg", "删除成功");
    }
    return resultMap;
  }
}
