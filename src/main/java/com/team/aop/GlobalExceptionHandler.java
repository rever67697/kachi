package com.team.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 全局捕获controller的异常处理
 * 创建日期：2018-1-23下午4:40:49
 * author:wuzhiheng
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ReturnMsg handler(Exception e){
		e.printStackTrace();
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_UNKNOW;
		if(e instanceof KachiException){
			returnMsg.setMsg(((KachiException)e).getMsg());
		}
		return returnMsg;
	}
}
