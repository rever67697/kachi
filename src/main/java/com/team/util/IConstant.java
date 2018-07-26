package com.team.util;

import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-15下午12:46:31
 * author:wuzhiheng
 */
public class IConstant {
	
	//缓存库的名称，自定义
	public final static String CACHED_NAME = "userCache";
	
	public final static String USER_KEY = "'USER_'+#id";
	
	//定义返回约定好的信息
	public final static String CODE_SUCCESS = "200";
	public final static String CODE_ERROR = "404";
	public final static String CODE_UNKNOW = "500";
	public final static String MSG_SUCCESS = "操作成功！";
	public final static String MSG_ERROR = "操作失败！";
	public final static String MSG_UNKONW = "未知错误！";

	/**验证码**/
	public final static String VERIFICATIONCODE = "verificationCode";
	
	public final static String SESSION_USER_NAME = "kachi_user";
	
	public final static String SESSION_PERMISSION_MAP = "kachi_user_permission";

	public final static String KEYPAIR = "keyPair";
	
	public final static String NO_PERMISSION = "没有权限！";

}
