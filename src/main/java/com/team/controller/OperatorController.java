package com.team.controller;

import com.team.service.OperatorService;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午11:52 2018/4/24
 */
@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping("/list")
    public ResultList list(Integer countryCode,int page,int rows){
        return operatorService.list(countryCode, page, rows);
    }

    @PostMapping("/selectedList")
    public ResultList selectedList(String operatorList){
        return operatorService.list(operatorList);
    }


}
