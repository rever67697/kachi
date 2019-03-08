package com.team.service.impl;

import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-2-3上午12:21:31
 * author:wuzhiheng
 */
public class BaseService {

	protected ReturnMsg successTip(){
		return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS);
	}

	protected ReturnMsg successTip(Object object){
		return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS,object);
	}

	protected ReturnMsg errorTip(){
		return new ReturnMsg(IConstant.CODE_ERROR, IConstant.MSG_ERROR);
	}

	protected ReturnMsg errorTip(String msg){
		return new ReturnMsg(IConstant.CODE_ERROR, msg);
	}

	protected Integer getDId(){
		return CommonUtil.getDId();
	}

	protected Integer getDepartmentId(){
		return CommonUtil.getDepartmentId();
	}
}
