package com.team.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 创建日期：2017-12-19下午6:39:34
 * author:wuzhiheng
 */
public class CommonUtil {
	private static char[] codes = { '1','2', '3', '4', '5', '6', '7', '8', '9','0'};
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
	 * 只要用作把Long参数放进Map中，如果参数为空，返回null
	 *@param str
	 *@return
	 *return
	 */
	public static Long putLong(String str){
		return str!=null&&!"".equals(str)&&str.matches("^\\d{1,}$")?Long.valueOf(str):null;
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
			}
			return Integer.valueOf(str);
		}
		return null;
	}
	/**
	 * 读取excel应该为long的数据
	 *@param cell
	 *@return
	 *return
	 */
	public static Long getCellLongVal(Cell cell){
		if(cell != null){
			String str = cell.toString();
			int index = str.indexOf(".0");
			if(index > -1 && index == str.length()-2){
				str = str.replace(".0", "");
			}
			return Long.valueOf(str);
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
	/**
	 * 读取excel应该为Double的数据
	 *@param cell
	 *@return
	 *return
	 */
	public static Double getCellDoubleVal(Cell cell){
		if(cell != null){
			return cell.getNumericCellValue();
		}
		return null;
	}
	/**
	 * 读取excel应该为Date的数据
	 *@param cell
	 *@return
	 *return
	 */
	public static Date getCellDateVal(Cell cell){
		if(cell != null){
			return cell.getDateCellValue();
		}
		return null;
	}
	/**
	 * 返回一个1开头的8位数的整形数字
	 *@return
	 *return
	 */
	public static Integer getNewId(){
		String id = "1";
		Random random = new Random();
		for(int i=0;i<7;i++){
			id += random.nextInt(9);
		}
		return Integer.valueOf(id);
	}
	
	/**
	 * 判断一个字符串是否为空
	 *@param str
	 *@return
	 *return
	 */
	public static boolean StringIsNull(String str){
		return str==null||"".equals(str)||"null".equals(str)?true:false;
	}
	
	public static String generateCode(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(codes[random.nextInt(codes.length-1)]);
		}
		return sb.toString();
	}
	
	/**
	 * 在内存中创建图像
	 * 
	 * @param verificationCodes
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage generateVerificationImage(
			char[] verificationCodes) throws Exception {
		// 设定长宽
		int width = 120, height = 36;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// String sRand = "";
		int i = 0;
		for (char c : verificationCodes) {
			// String rand = String.valueOf(random.nextInt(10));
			// sRand += c;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(c + "", 25 * i++ + 12, 27);
		}
		// 将认证码存入SESSION
		// ActionContext.getContext().getSession().put("rand", sRand);
		// 图象生效
		g.dispose();
		return image;
	}
	
	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
