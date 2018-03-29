package com.team.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.model.SimCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.service.SimCardService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

import java.io.*;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
@PermissionLog("流量卡管理")
@RequestMapping("/simcard")
public class SimCardController {

	@Autowired
	private SimCardService simCardService;

	/**
	 * 根据卡池id找出对应的卡
	 *@param cpId
	 *@return
	 *return
	 */
	@GetMapping("/getByPool")
	@PermissionLog(key="cpId_卡池编号;name_卡池名称")
	public ReturnMsg getByPool(Integer cpId){
		return simCardService.getSimCardByPool(cpId);
	}
	
	@PostMapping("/list")
	public ResultList list(SimCard simCard,int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simCardService.getSimCardList(simCard,dId, page, rows);
	}

	@GetMapping("/getCsv")
	@PermissionLog
	public void getCsv(SimCard simCard, HttpServletRequest request, HttpServletResponse response) throws  Exception{
		Integer dId = CommonUtil.getUser(request).getDepartmentId();

		File file = simCardService.getCsv(simCard,dId);

		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload");// application/msexcel
		response.setHeader("Content-Disposition", "attachment; filename="
				+file.getName());
		FileInputStream in = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			in = new FileInputStream(file);
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(file.exists()){
				file.delete();
			}
		}
	}

	@PostMapping("/outlineInfo")
	public ReturnMsg outlineInfo(HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simCardService.getOutlineInfo(dId);
	}
	
	@PostMapping("/delete")
	@PermissionLog(key="IMSIs_IMSI的集合")
	public ReturnMsg delete(String ids){
		return simCardService.deleteSimCard(ids);
	}

	@PostMapping("/update")
	@PermissionLog(key="imsi_IMSI;isChangePeriod_账期是否改变;isChangePackage_套餐是否改变")
	public ReturnMsg update(SimCard simCard,boolean isChangePeriod,boolean isChangePackage){
		ReturnMsg returnMsg = simCardService.update(simCard,isChangePeriod,isChangePackage);
		return returnMsg;
	}

	@PostMapping("/batchUpdate")
	@PermissionLog(key = "status_卡状态;packageId_卡套餐;provinceCode_省;offPeriod_帐期日;sustained_账期持续时间;" +
			"expiryDate_有效期截止时间;openDate_开卡时间;usedVpn_是否支持vpn;softType_是否软卡;ids_ids")
	public ReturnMsg batchUpdate(SimCard simCard,String ids){
		return simCardService.batchUpdate(simCard,ids);
	}
	
}
