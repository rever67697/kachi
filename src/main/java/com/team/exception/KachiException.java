package com.team.exception;
/**
 * 创建日期：2018-1-23下午4:33:18
 * author:wuzhiheng
 */
public class KachiException extends RuntimeException{

	private String msg;
	
	public KachiException() {
		super();
	}

	public KachiException(String msg){
		super(msg);
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
