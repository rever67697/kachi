package com.team.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.model.SimCard;
import com.team.service.TestService;

/**
 * 创建日期：2017-12-16下午10:44:00
 * author:wuzhiheng
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

	@Autowired
	private TestService testService;
	
	@Test
	public void testPagePlugin(){
		PageHelper.startPage(1, 3);
		List<SimCard> list = testService.getAll();
		PageInfo<SimCard> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		for (SimCard simCard : list) {
			System.out.println(simCard);
		}
	}
	
}
