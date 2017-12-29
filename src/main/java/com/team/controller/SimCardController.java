package com.team.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.SimCardService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
public class SimCardController {

	@Autowired
	private SimCardService simCardService;
	
	/**
	 * 根据卡池id找出对应的卡
	 *@param cpId
	 *@return
	 *return
	 */
	@GetMapping("/getSimCardByPool/{cpId}")
	public ResultList getSimCardByPool(@PathVariable("cpId")int cpId){
		return simCardService.getSimCardByPool(cpId);
	}
	
	@PostMapping("/getSimCard")
	public ResultList getSimCard(String departmentId,String cpId,String number,String status,int page,int rows){
		return simCardService.getSimCard(departmentId, cpId, number, status, page, rows);
	}
	
	@PostMapping("/getCardOutlineInfo")
	public ReturnMsg getPoolOutlineInfo(String departmentId){
		return simCardService.getOutlineInfo(departmentId);
	}
	
}
