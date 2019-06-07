package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.RuleSpeed;
import com.team.service.RuleSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 16:05 2019-06-06
 */
@RestController
@RequestMapping("/ruleSpeed")
@PermissionLog("限速配置")
public class RuleSpeedController {

    @Autowired
    private RuleSpeedService ruleSpeedService;

    @PostMapping("/list")
    public Object list(Integer departmentId,int page,int rows){
        return ruleSpeedService.list(departmentId, page, rows);
    }

    @PostMapping("/save")
    @PermissionLog
    public Object save(RuleSpeed ruleSpeed){
        return ruleSpeedService.saveOrUpdate(ruleSpeed);
    }


    @PostMapping("/delete")
    @PermissionLog
    public Object delete(Integer id){
        return ruleSpeedService.delete(id);
    }

}
