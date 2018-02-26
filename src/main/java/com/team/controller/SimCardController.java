package com.team.controller;


import javax.servlet.http.HttpServletRequest;

import com.team.model.SimCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.service.SimCardService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
@PermissionLog("流量卡管理")
public class SimCardController {

	@Autowired
	private SimCardService simCardService;

	/**
	 * 根据卡池id找出对应的卡
	 *@param cpId
	 *@return
	 *return
	 */
	@GetMapping("/getSimCardByPool")
	@PermissionLog(key="cpId_卡池编号;name_卡池名称")
	public ReturnMsg getSimCardByPool(Integer cpId){
		return simCardService.getSimCardByPool(cpId);
	}
	
	@PostMapping("/getSimCardList")
	public ResultList getSimCard(Integer departmentId,Integer cpId,String number,
			Integer status,int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simCardService.getSimCardList(departmentId, dId,cpId, number, status, page, rows);
	}
	
	@PostMapping("/getCardOutlineInfo")
	public ReturnMsg getPoolOutlineInfo(HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simCardService.getOutlineInfo(dId);
	}
	
	@PostMapping("/deleteSimCard")
	@PermissionLog(key="IMSIs_IMSI的集合")
	public ReturnMsg deleteSimCard(String ids){
		return simCardService.deleteSimCard(ids);
	}

	@PostMapping("/updateSimCard")
	@PermissionLog(key="imsi_IMSI；isChangePeriod_帐期是否改变;isChangePackage_套餐是否改变")
	public ReturnMsg updateSimCard(SimCard simCard,boolean isChangePeriod,boolean isChangePackage){

		return simCardService.update(simCard,isChangePeriod,isChangePackage);
	}
	
}
