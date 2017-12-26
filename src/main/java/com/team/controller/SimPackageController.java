package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.SimPackage;
import com.team.service.SimCardService;
import com.team.service.SimPackageService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23下午6:52:19
 * author:wuzhiheng
 */
@RestController
public class SimPackageController {

	@Autowired
	private SimPackageService simPackageService;
	@Autowired
	private SimCardService simCardService;
	
	@PostMapping("/getSimPackage")
	public ResultList getSimPackage(String status,String name,int page,int rows){
		return simPackageService.getSimPackage(status, name, page, rows);
	}
	
	@PostMapping("/savePackage")
	public ReturnMsg savePackage(SimPackage simPackage){
		int count = 0;
		if(simPackage.getId()!=null){
			count = simPackageService.updatePackage(simPackage);
		}else{
			count = simPackageService.insertPackage(simPackage);
		}
		if(count > 0){
			return IConstant.MSG_OPERATE_SUCCESS;
		}
		return IConstant.MSG_OPERATE_ERROR;
	} 
	
	@PostMapping("/deletePackageById")
	public ReturnMsg deleteTerminalByIds(Integer id){
		ReturnMsg returnMsg = null;
		if(!CommonUtil.StringIsNull(simCardService.getPackageExist(id))){
			returnMsg = IConstant.MSG_OPERATE_ERROR;
			returnMsg.setMsg("删除失败，有SIM卡正在使用该套餐！");
		}else{
			returnMsg = simPackageService.deletePackage(id);
		}
		return returnMsg;
	}
	
}
