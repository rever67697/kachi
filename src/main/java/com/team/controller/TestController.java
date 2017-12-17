package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.TestService;

/**
 * 创建日期：2017-12-11上午9:55:20
 * author:wuzhiheng
 */
@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	

	@GetMapping("/testSb")
	public Object test(){
		return "hello,this is springBoot!";
	}
	
	@GetMapping("/getAll")
	public Object testDb(){
		System.out.println("success");
		return testService.getAll();
	}
	
	@GetMapping("/getCustomer/{id}")
	public Object getCustomer(@PathVariable("id") int id){
		System.out.println("id===="+id);
		return testService.getCustomerById(id);
	}
	
}
