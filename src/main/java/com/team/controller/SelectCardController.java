package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.service.SelectCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object list(Date startDate,Date endDate, Integer tsid, int page, int rows){
        return selectCardService.list(startDate,endDate,tsid,page, rows);
    }

    @PostMapping("/listSelectCardLog")
    public Object listSelectCardLog(Date startDate,Date endDate, Integer tsid, Long imsi,int page, int rows){
        return selectCardService.listSelectCardLog(startDate,endDate,tsid,imsi,page, rows);
    }


}
