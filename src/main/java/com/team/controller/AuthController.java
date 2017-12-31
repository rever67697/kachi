package com.team.controller;


import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.service.impl.AuthServiceImpl;
import com.team.util.CommonUtil;



/**
 * 创建日期：2017-12-18下午1:01:56
 * author:wuzhiheng
 */
@Controller
public class AuthController {
	
	@Autowired
	private AuthServiceImpl authService;
	
	@GetMapping("/getMenu")
	@ResponseBody
	public Object getMenu(){
		return authService.queryMenuByUser();
	}
	
	@GetMapping("/verificationCode")
	public void verificationCode(HttpServletRequest request,HttpServletResponse response){
		// 生成验证码并放入session中
		String verificationCodes = CommonUtil.generateCode(4);
		request.getSession().setAttribute("varificationCode",verificationCodes);
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
}
