package com.team.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.aop.PermissionLog;
import com.team.model.ChannelCard;
import com.team.service.ChannelCardService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * @author Wzq 2017年12月21日 下午8:33:47
 * 
 */
@RestController
@PermissionLog("副卡管理")
@SuppressWarnings("all")
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
	@PostMapping("/getChannelCardList")
	public ResultList getChannelCardList(Integer departmentId,String number,Integer countryCode,Integer operatorCode, Integer status, 
			int page, int rows,HttpServletRequest request) {
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return channelCardService.getChannelCardList(departmentId,dId,number,countryCode, operatorCode, status, page, rows);
	}

	@PostMapping("/deleteChannelCards")
	@PermissionLog(key="IMSIs_IMSI的集合")
	public ReturnMsg deleteChannelCards(String ids){
		return channelCardService.deleteChannelCards(ids);
	}
	
	@PostMapping("/saveChannelCard")
	@PermissionLog(key="imsi_IMSI")
	public ReturnMsg saveChannelCard(ChannelCard channelCard){
		return channelCardService.saveChannelCard(channelCard);
	}
	
	@PostMapping("/uploadChannelCard")
	@PermissionLog
	public ReturnMsg uploadChannelCard(MultipartFile file){
		ReturnMsg returnMsg = channelCardService.getChannelCardList(file);
		if("200".equals(returnMsg.getCode())){
			List<ChannelCard> list = (List<ChannelCard>) returnMsg.getData();
			channelCardService.insertBatch(list);
			returnMsg.setData(list.size());
		}
		return returnMsg;
	} 
	
}