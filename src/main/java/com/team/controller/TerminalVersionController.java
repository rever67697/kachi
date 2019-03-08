package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.TerminalVersion;
import com.team.service.TerminalVersionService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 终端app版本
 * @Date Created in 下午6:24 2018/3/20
 */
@RestController
@PermissionLog("App版本管理")
@RequestMapping("/tv")
public class TerminalVersionController {

    @Autowired
    private TerminalVersionService terminalVersionService;

    @PostMapping("/list")
    public ResultList list(int page,int rows){
        return terminalVersionService.list(page,rows);
    }

    @PostMapping("/save")
    @PermissionLog()
    public ReturnMsg saveTV(TerminalVersion terminalVersion,MultipartFile file) throws Exception{
        String operatorMan = CommonUtil.getUser().getName();
        terminalVersion.setOperatorMan(operatorMan);
        terminalVersion.setOperatorTime(new Date());
        return terminalVersionService.saveOrUpdate(terminalVersion,file);
    }

    @PostMapping("/delete")
    @PermissionLog
    public ReturnMsg save(String ids){
        return terminalVersionService.delete(ids);
    }

}
