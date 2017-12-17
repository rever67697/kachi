package com.team.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 创建日期：2017-12-14上午9:18:26
 * author:wuzhiheng
 */
@Controller
public class ThymeleafTestController {

	@GetMapping("/testTemplate")
	public String testTemplate(Model model){
		return "testThymeleaf";
	}
}
