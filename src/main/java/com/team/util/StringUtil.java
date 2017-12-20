package com.team.util;
/**
 * 创建日期：2017-12-19下午6:39:34
 * author:wuzhiheng
 */
public class StringUtil {
	
	/**
	 * 只要用作把Integer参数放进Map中，如果参数为空，返回null
	 *@param str
	 *@return
	 *return
	 */
	public static Integer putInteger(String str){
		return str!=null&&!"".equals(str)&&str.matches("^\\d{1,}$")?Integer.valueOf(str):null;
	}
	
}
