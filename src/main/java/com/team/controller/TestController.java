package com.team.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建日期：2017-12-10下午3:36:09
 * author:wuzhiheng
 */
@RestController
public class TestController {

	
	@GetMapping("/test")
	public String test(){
		return "hello world!";
	}
}
