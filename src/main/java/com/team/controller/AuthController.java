package com.team.controller;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.model.auth.TbAuthUser;
import com.team.service.impl.AuthServiceImpl;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;



/**
 * 创建日期：2017-12-18下午1:01:56
 * author:wuzhiheng
 */
@Controller
public class AuthController {
	
	@Autowired
	private AuthServiceImpl authService;
	
	@PostMapping("/getMenu")
	@ResponseBody
	public Object getMenu(){
		return authService.queryMenuByUser();
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
	
	@PostMapping("/login")
	@ResponseBody
	public ReturnMsg login(TbAuthUser user,String code,HttpServletRequest request){
		ReturnMsg returnMsg = null;
		String msg = "";
		String verificationCode = (String) request.getSession().getAttribute("verificationCode");
		if(verificationCode != null && verificationCode.equals(code)){
			if("admin".equals(user.getUserName()) && "123".equals(user.getPassWord())){
				request.getSession().setAttribute("kachi_user", user);
			}else{
				msg = "用户名或密码错误！";
			}
		}else{
			msg = "验证码错误！";
		}
		if(CommonUtil.StringIsNull(msg)){
			returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		}else{
			returnMsg = IConstant.MSG_OPERATE_ERROR;
			returnMsg.setMsg(msg);
		}
		return returnMsg;
	}
	
	@PostMapping("/getUser")
	@ResponseBody
	public TbAuthUser getUser(HttpServletRequest request){
		return (TbAuthUser) request.getSession().getAttribute("kachi_user");
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public ReturnMsg logout(HttpServletRequest request){
		request.getSession().removeAttribute("kachi_user");
		return IConstant.MSG_OPERATE_SUCCESS;
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
	
	@GetMapping("/getCode")
	@ResponseBody
	public String getcode(HttpServletRequest request) throws Exception{
		int index = new Random().nextInt(49);
		Resource resource = new ClassPathResource("/static/images/code/"+index+".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		String code= br.readLine().trim();
		request.getSession().setAttribute("verificationCode", code);
		br.close();
		return "../images/code/"+index+".jpg";
	}
}
