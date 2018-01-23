package com.team.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-23下午4:40:49
 * author:wuzhiheng
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(KachiException.class)
	@ResponseBody
	public ReturnMsg noPermission(KachiException e){
		e.printStackTrace();
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_UNKNOW;
		returnMsg.setMsg(e.getMsg());
		return returnMsg;
	}
}
