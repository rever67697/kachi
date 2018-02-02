package com.team.service.impl;

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
	
	protected ReturnMsg errorTip(){
		return new ReturnMsg(IConstant.CODE_ERROR, IConstant.MSG_ERROR);
	}
	
	protected ReturnMsg unknowTip(){
		return new ReturnMsg(IConstant.CODE_UNKNOW, IConstant.MSG_UNKONW);
	}
	
}
