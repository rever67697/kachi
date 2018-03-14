package com.team.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

import javax.servlet.http.HttpServletRequest;

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
		ReturnMsg returnMsg = new ReturnMsg(IConstant.CODE_UNKNOW, IConstant.MSG_UNKONW);
		if(e instanceof KachiException){
			returnMsg.setMsg(((KachiException)e).getMsg());
		}
		return returnMsg;
	}

	private boolean isAjax(HttpServletRequest request){
		return (request.getHeader("X-Request-With") != null &&
				"XMLHttpRequest".equals(request.getHeader("X-Request-With").toString()));
	}
}
