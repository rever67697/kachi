package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.SimCardService;

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
	public Object getSimCardByPool(@PathVariable("cpId")int cpId){
		return simCardService.getSimCardByPool(cpId);
	}
	
}
