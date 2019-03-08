package com.team.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.annotation.PermissionLog;
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
@RequestMapping("/channelCard")
@SuppressWarnings("all")
public class ChannelCardController {
	
	@Autowired
	private ChannelCardService channelCardService;

	/**
	 * 查询获取副卡列表
	 * @return
	 */
	@PostMapping("/list")
	public ResultList list(Integer departmentId,Integer tsid,Long imsi,Integer countryCode,Integer operatorCode, Integer status,
			int page, int rows) {
		return channelCardService.getChannelCardList(tsid,imsi,departmentId ,page, rows);
	}

	@PostMapping("/delete")
	@PermissionLog
	public ReturnMsg delete(String ids){
		return channelCardService.deleteChannelCards(ids);
	}
	
	@PostMapping("/save")
	@PermissionLog
	public ReturnMsg save(ChannelCard channelCard){
		return channelCardService.saveChannelCard(channelCard);
	}
	
	@PostMapping("/upload")
	@PermissionLog
	public ReturnMsg upload(MultipartFile file){
		ReturnMsg returnMsg = channelCardService.getChannelCardList(file);
		if("200".equals(returnMsg.getCode())){
			List<ChannelCard> list = (List<ChannelCard>) returnMsg.getData();
			channelCardService.insertBatch(list);
			returnMsg.setData(list.size());
		}
		return returnMsg;
	}

}