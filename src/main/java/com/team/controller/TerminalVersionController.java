package com.team.controller;

import com.team.aop.PermissionLog;
import com.team.model.TerminalVersion;
import com.team.service.TerminalVersionService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 终端app版本
 * @Date Created in 下午6:24 2018/3/20
 */
@RestController
@PermissionLog("App版本管理")
public class TerminalVersionController {

    @Autowired
    private TerminalVersionService terminalVersionService;

    @PostMapping("/tvList")
    public ResultList list(int page,int rows){
        return terminalVersionService.list(page,rows);
    }

    @PostMapping("/saveTV")
    @PermissionLog(key = "tVersion_目标版本号;oVersion_源版本号;downUrl_下载地址")
    public ReturnMsg saveTV(TerminalVersion terminalVersion, HttpServletRequest request){
        String operatorMan = CommonUtil.getUser(request).getName();
        terminalVersion.setOperatorMan(operatorMan);
        terminalVersion.setOperatorTime(new Date());
        return terminalVersionService.saveOrUpdate(terminalVersion);
    }
}
