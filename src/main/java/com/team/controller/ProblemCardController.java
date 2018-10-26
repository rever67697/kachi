package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.service.ProblemCardService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:10 PM 2018/10/25
 */
@RestController
@PermissionLog("问题卡")
@RequestMapping("/problemCard")
public class ProblemCardController {

    @Autowired
    private ProblemCardService problemCardService;


    @PostMapping("/list")
    public ResultList list(Date startDate, Date endDate, Integer tsid, Long imsi,Integer status, int page, int rows){
        return problemCardService.list(startDate,endDate,tsid,imsi,status,page,rows);
    }

    @PostMapping("/delete")
    @PermissionLog
    public ReturnMsg delete(Long imsi){
        return problemCardService.delete(imsi);
    }
}
