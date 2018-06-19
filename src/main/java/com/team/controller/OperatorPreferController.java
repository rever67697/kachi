package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.OperatorPrefer;
import com.team.service.OperatorPreferService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:53 2018/4/23
 */
@RestController
@PermissionLog("运营商配置")
@RequestMapping("/operatorPrefer")
public class OperatorPreferController {

    @Autowired
    private OperatorPreferService operatorPreferService;

    @PostMapping("/list")
    public ResultList list(Integer countryCode,Integer operatorCode,int page,int rows){
        return operatorPreferService.list(countryCode,operatorCode,page,rows);
    }

    @PostMapping("/save")
    @PermissionLog
    public ReturnMsg save(OperatorPrefer operatorPrefer){
        return operatorPreferService.saveOrUpdate(operatorPrefer);
    }

    @PostMapping("/delete")
    @PermissionLog
    public ReturnMsg save(String ids){
        return operatorPreferService.delete(ids);
    }
}
