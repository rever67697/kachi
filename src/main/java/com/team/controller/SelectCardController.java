package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.service.SelectCardService;
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
public class SelectCardController {

    @Autowired
    private SelectCardService selectCardService;

    @PostMapping("/list")
    public Object list(Date startDate,Date endDate, Integer tsid, Integer departmentId, int page, int rows, HttpServletRequest request){
        Integer dId = CommonUtil.getUser(request).getDepartmentId();
        return selectCardService.list(startDate,endDate,tsid,departmentId,dId,page,rows);
    }

    @PostMapping("/listSelectCardLog")
    public Object listSelectCardLog(Date startDate,Date endDate, Integer tsid, Long imsi,Integer departmentId,int page, int rows, HttpServletRequest request){
        Integer dId = CommonUtil.getUser(request).getDepartmentId();
        return selectCardService.listSelectCardLog(startDate,endDate,tsid,imsi,departmentId,dId,page, rows);
    }


}
