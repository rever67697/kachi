package com.team.util;

import org.apache.poi.ss.usermodel.Cell;

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
	
	/**
	 * 读取excel应该为整数类型的数据
	 *@param cell
	 *@return
	 *return
	 */
	public static Integer getCellIntVal(Cell cell){
		if(cell != null){
			String str = cell.toString();
			int index = str.indexOf(".0");
			if(index > -1 && index == str.length()-2){
				str = str.replace(".0", "");
			}else{
				return null;
			}
			return Integer.valueOf(str);
		}
		return null;
	}
	/**
	 * 读取excel应该为整数字符串的数据
	 *@param cell
	 *@return
	 *return
	 */
	public static String getCellStringVal(Cell cell){
		if(cell != null){
			String str = cell.toString();
			int index = str.indexOf(".0");
			if(index > -1 && index == str.length()-2){
				str = str.replace(".0", "");
			}
			return str;
		}
		return null;
	}
	
}
