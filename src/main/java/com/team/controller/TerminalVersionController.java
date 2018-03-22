package com.team.controller;

import com.team.service.TerminalVersionService;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description : 终端app版本
 * @Date Created in 下午6:24 2018/3/20
 */
@RestController
public class TerminalVersionController {

    @Autowired
    private TerminalVersionService terminalVersionService;

    @PostMapping("/tvList")
    public ResultList list(int page,int rows){
        return terminalVersionService.list(page,rows);
    }
}
