package com.team.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.jobs.SendMessageTask;
import com.team.service.CountryService;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private CountryService countryService;

	@PostMapping("/getCountryDic")
	public ReturnMsg getCountryDic(){
		return commonService.getCountryDic();
	}

	@PostMapping("/getDepartmentDic")
	public ReturnMsg getDepartmentDic(){
		return commonService.getDepartmentDic();
	}

	@PostMapping("/getOperatorDic")
	public ReturnMsg getOperatorDic(Integer countryCode,Integer mcc){
		return commonService.getOperatorDic(countryCode,mcc);
	}

	@PostMapping("/getSimPoolDic")
	public ReturnMsg getSimPoolDic(){
		return commonService.getSimPoolDic();
	}

	@PostMapping("/getPackageDic")
	public ReturnMsg getPackageDic(Integer operatorCode){
		return commonService.getPackageDic(operatorCode);
	}

	@PostMapping("/getProvinceDic")
	public ReturnMsg getProvinceDic(@RequestParam(name = "countryCode",defaultValue = "156") Integer countryCode){
		return commonService.getProvinceDic(countryCode);
	}

	@PostMapping("/getMccDic")
	public ReturnMsg getMccDic(){
		return commonService.getMccDic();
	}

	@PostMapping("/countryList")
	public ResultList countryList(String nameCn,int page,int rows){
		return countryService.list(nameCn, page, rows);
	}

	@PostMapping("/countrySelectedList")
	public ResultList countrySelectedList(String mccs){
		return countryService.getList(mccs);
	}

	/**
	 * 通过imsi查询当前卡缓存状况
	 * @param ismi
	 * @return
	 */
	@GetMapping("/q")
	@ResponseBody
	public Object q(@RequestParam("imsi") Long ismi){
		return commonService.q(ismi);
	}

	@GetMapping("/downloadFile")
	public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request) throws Exception{
		fileName = URLDecoder.decode(fileName, "utf-8");
		Resource resource = new ClassPathResource("/static/file/"+fileName);
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		if (userAgent.contains("MSIE") || userAgent .contains("TRIDENT")) {
			try {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
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

	@GetMapping("/download")
	public void download(String fileName,HttpServletResponse response) throws Exception{
		fileName = URLDecoder.decode(fileName, "utf-8");
//		if (request.getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0) {
//			try {
//				fileName = URLEncoder.encode(fileName, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		} else {
//			try {
//				fileName = new String(fileName.getBytes(), "ISO-8859-1");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}

		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application//octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName.replaceAll("^\\d+-",""));
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File("kachi/file/"+fileName)));
			//设置响应的内容长度
			response.setContentLength(bis.available());
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
		request.getSession().setAttribute(IConstant.VERIFICATIONCODE, code);
		br.close();
		return "../images/code/"+index+".jpg";
	}
	
	@GetMapping("/verificationCode")
	public void verificationCode(HttpServletRequest request,HttpServletResponse response){
		// 生成验证码并放入session中
		String verificationCodes = CommonUtil.generateCode(4);
		request.getSession().setAttribute(IConstant.VERIFICATIONCODE,verificationCodes);
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

	@GetMapping("/map")
	@ResponseBody
	public Object map(){
		return SendMessageTask.MAP;
	}

	@GetMapping("/clearMap")
	public void clearMap(){
		SendMessageTask.MAP.clear();
	}

}
