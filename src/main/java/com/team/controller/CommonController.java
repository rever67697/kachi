package com.team.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.CommonService;
import com.team.util.CommonUtil;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23上午1:59:12
 * author:wuzhiheng
 */
@RestController
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/getCountryDic")
	public ReturnMsg getCountryDic(){
		return commonService.getCountryDic();
	}
	
	@PostMapping("/getDepartmentDic")
	public ReturnMsg getDepartmentDic(HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return commonService.getDepartmentDic(dId);
	}
	
	@PostMapping("/getOperatorDic")
	public ReturnMsg getOperatorDic(Integer countryCode){
		return commonService.getOperatorDic(countryCode);
	}
	
	@PostMapping("/getSimPoolDic")
	public ReturnMsg getSimPoolDic(HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return commonService.getSimPoolDic(dId);
	}

	@GetMapping("/downloadFile")
	public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request) throws Exception{
		fileName = URLDecoder.decode(fileName, "utf-8");
		Resource resource = new ClassPathResource("/static/file/"+fileName);
		if (request.getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0) {
			try {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				fileName = new String(fileName.getBytes(), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload");// application/msexcel
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(resource.getInputStream());
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	@GetMapping("/getCode")
	public String getcode(HttpServletRequest request) throws Exception{
		int index = new Random().nextInt(49);
		Resource resource = new ClassPathResource("/static/images/code/"+index+".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		String code= br.readLine().trim();
		request.getSession().setAttribute("verificationCode", code);
		br.close();
		return "../images/code/"+index+".jpg";
	}
	
	@GetMapping("/verificationCode")
	public void verificationCode(HttpServletRequest request,HttpServletResponse response){
		// 生成验证码并放入session中
		String verificationCodes = CommonUtil.generateCode(4);
		request.getSession().setAttribute("verificationCode",verificationCodes);
		try {
			// 禁止图像缓存
			// 创建二进制的输出流
			ServletOutputStream sos = response.getOutputStream();
			// 将图像输出到Servlet输出流中
			BufferedImage image = CommonUtil.generateVerificationImage(verificationCodes.toCharArray());
			ImageIO.write(image, "jpeg", sos);
			sos.flush();
			sos.close();
			sos = null;
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getImage")
	public void getImage() throws Exception{
		for (int i = 0; i < 50; i++) {
			String s = CommonUtil.generateCode(4);
			BufferedImage image = CommonUtil.generateVerificationImage( s.toCharArray());
			FileOutputStream b1 = new FileOutputStream(new File("E:/img/"+i+".jpg")); 
			FileOutputStream b2 = new FileOutputStream(new File("E:/img/"+i+".txt")); 
			BufferedOutputStream buffer = new BufferedOutputStream(b2);
			buffer.write(s.getBytes());
			ImageIO.write(image, "jpeg", b1);
			
			buffer.flush();
			buffer.close();
			b1.close();
			b2.close();
		}
	}
}
