package com.team.vo;
/**
 * 通过返回一般Ajax请求返回的结果
 * 创建日期：2017-12-22上午12:11:03
 * author:wuzhiheng
 */
public class ReturnMsg {

	private String code;
	
	private String msg;
	
	private Object data;
	

	public ReturnMsg(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	
	public ReturnMsg(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	
	
}
