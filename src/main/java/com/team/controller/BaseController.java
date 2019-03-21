package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : wuzhiheng
 * @Description : 给子类提供一些方法和参数
 * @Date Created in 09:43 2019-03-08
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

}
