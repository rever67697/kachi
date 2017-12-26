package com.team.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 创建日期：2017-12-11上午9:55:20
 * author:wuzhiheng
 */
@RestController
public class TestController {
	
	

	@GetMapping("/testSb")
	public Object test(){
		return "hello,this is springBoot!";
	}
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request,MultipartFile file) throws Exception{
		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		Map<String, Object> map = new HashMap<>();
		
		try {
            book = WorkbookFactory.create(file.getInputStream());
            sheet = book.getSheetAt(1);
            int total = sheet.getLastRowNum()+1;
            System.out.println("total==="+total);
            for (int i = 0; i < total; i++) {
				row = sheet.getRow(i);
				if(row == null || row.getCell(1) == null){
					continue;
				}
				System.out.println(row.getCell(0).toString()+"\t"+row.getCell(1).toString()+"\t"+row.getCell(2).toString()+"\t"+row.getCell(3).toString());
			}
            map.put("msg", "ok");
        } catch (Exception e) {  
            e.printStackTrace(); 
            map.put("msg", "error");
        }finally{
        	return map;
        }
	}
	
}
