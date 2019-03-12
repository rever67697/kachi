package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.SendMesagePhone;
import com.team.service.SelectCardService;
import com.team.service.SendMessagePhoneService;
import com.team.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 4:45 PM 2018/10/25
 */
@RestController
@PermissionLog("选卡")
@RequestMapping("/selectCard")
public class SelectCardController{

    @Autowired
    private SelectCardService selectCardService;

    @Autowired
    private SendMessagePhoneService sendMessagePhoneService;

    /**
     * 取卡异常
     * @return
     */
    @PostMapping("/list")
    public Object listUnnormal(Date startDate,Date endDate, Integer tsid, Integer departmentId, int page, int rows){
        return selectCardService.listUnnormal(startDate,endDate,tsid,departmentId,page,rows);
    }

    /**
     * 选卡日志
     * @return
     */
    @PostMapping("/listSelectCardLog")
    public Object listSelectCardLog(Date startDate,Date endDate, Integer tsid, Long imsi,Integer departmentId,int page, int rows){
        return selectCardService.listSelectCardLog(startDate,endDate,tsid,imsi,departmentId,page, rows);
    }

    @PostMapping("/getPhone")
    public Object getPhone(){
        return sendMessagePhoneService.get();
    }

    @PostMapping("/savePhone")
    @PermissionLog
    public Object savePhone(SendMesagePhone sendMesagePhone){
        return sendMessagePhoneService.save(sendMesagePhone);
    }



}
