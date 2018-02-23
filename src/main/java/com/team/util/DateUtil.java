package com.team.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class DateUtil {
	
	private static final Logger logger =LoggerFactory.getLogger(DateUtil.class);
	
	public static final String RB_DATE_FORMATER = "yyyy-MM-dd HH:mm:ss";
	
	public static final String RB_DATE_Y_M_D = "yyyy-MM-dd";
	public static final String RB_DATE_Y_M = "yyyy-MM";
	
	public static final String SYS_DEF_FORMATER="EEE MMM d H:m:s z y";
	
	/**
	 * 对日期(时间)中的日进行加减计算. <br>
	 * 例子: <br>
	 * 如果Date类型的d为 2005年8月20日,那么 <br>
	 * calculateByDate(d,-10)的值为2005年8月10日 <br>
	 * 而calculateByDate(d,+10)的值为2005年8月30日 <br>
	 * 
	 * @param d
	 *            日期(时间).
	 * @param amount
	 *            加减计算的幅度.+n=加n天;-n=减n天.
	 * @return 计算后的日期(时间).
	 */
	public static Date calculateByDate(Date d, int amount) {
		return calculate(d, GregorianCalendar.DATE, amount);
	}

	public static Date calculateByMinute(Date d, int amount) {
		return calculate(d, GregorianCalendar.MINUTE, amount);
	}
	
	public static Date calculateByHour(Date d, int amount) {
		return calculate(d, GregorianCalendar.HOUR_OF_DAY, amount);
	}

	public static Date calculateByYear(Date d, int amount) {
		return calculate(d, GregorianCalendar.YEAR, amount);
	}
	
	public static Date calculateByMonth(Date d, int amount) {
		return calculate(d, GregorianCalendar.MONTH, amount);
	}

	/**
	 * 对日期(时间)中由field参数指定的日期成员进行加减计算. <br>
	 * 例子: <br>
	 * 如果Date类型的d为 2005年8月20日,那么 <br>
	 * calculate(d,GregorianCalendar.YEAR,-10)的值为1995年8月20日 <br>
	 * 而calculate(d,GregorianCalendar.YEAR,+10)的值为2015年8月20日 <br>
	 * 
	 * @param d
	 *            日期(时间).
	 * @param field
	 *            日期成员. <br>
	 *            日期成员主要有: <br>
	 *            年:GregorianCalendar.YEAR <br>
	 *            月:GregorianCalendar.MONTH <br>
	 *            日:GregorianCalendar.DATE <br>
	 *            时:GregorianCalendar.HOUR <br>
	 *            分:GregorianCalendar.MINUTE <br>
	 *            秒:GregorianCalendar.SECOND <br>
	 *            毫秒:GregorianCalendar.MILLISECOND <br>
	 * @param amount
	 *            加减计算的幅度.+n=加n个由参数field指定的日期成员值;-n=减n个由参数field代表的日期成员值.
	 * @return 计算后的日期(时间).
	 */
	public static Date calculate(Date d, int field, int amount) {
		if (d == null)
			return null;
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(d);
		g.add(field, amount);
		return g.getTime();
	}

	/**
	 * 日期(时间)转化为字符串.
	 * 
	 * @param formater
	 *            日期或时间的格式.
	 * @param aDate
	 *            java.util.Date类的实例.
	 * @return 日期转化后的字符串.
	 */
	public static String date2String(String formater, Date aDate) {
		if (formater == null || "".equals(formater))
			return null;
		if (aDate == null)
			return null;
		return (new SimpleDateFormat(formater)).format(aDate);
	}

	/**
	 * 当前日期(时间)转化为字符串.
	 * 
	 * @param formater
	 *            日期或时间的格式.
	 * @return 日期转化后的字符串.
	 */
	public static String date2String(String formater) {
		return date2String(formater, new Date());
	}

	/**
	 * 获取当前日期对应的星期数. <br>
	 * 1=星期天,2=星期一,3=星期二,4=星期三,5=星期四,6=星期五,7=星期六
	 * 
	 * @return 当前日期对应的星期数
	 */
	public static int dayOfWeek() {
		GregorianCalendar g = new GregorianCalendar();
		int ret = g.get(java.util.Calendar.DAY_OF_WEEK);
		g = null;
		return ret;
	}

	/**
	 * 获取所有的时区编号. <br>
	 * 排序规则:按照ASCII字符的正序进行排序. <br>
	 * 排序时候忽略字符大小写.
	 * 
	 * @return 所有的时区编号(时区编号已经按照字符[忽略大小写]排序).
	 */
	public static String[] fecthAllTimeZoneIds() {
		Vector v = new Vector();
		String[] ids = TimeZone.getAvailableIDs();
		for (int i = 0; i < ids.length; i++) {
			v.add(ids[i]);
		}
		java.util.Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
		v.copyInto(ids);
		v = null;
		return ids;
	}
	
	/**
	 * 将日期时间字符串根据转换为指定时区的日期时间.
	 * 
	 * @param srcFormater
	 *            待转化的日期时间的格式.
	 * @param srcDateTime
	 *            待转化的日期时间.
	 * @param dstFormater
	 *            目标的日期时间的格式.
	 * @param dstTimeZoneId
	 *            目标的时区编号.
	 * 
	 * @return 转化后的日期时间.
	 */
	public static String string2Timezone(String srcFormater,
			String srcDateTime, String dstFormater, String dstTimeZoneId) {
		if (srcFormater == null || "".equals(srcFormater))
			return null;
		if (srcDateTime == null || "".equals(srcDateTime))
			return null;
		if (dstFormater == null || "".equals(dstFormater))
			return null;
		if (dstTimeZoneId == null || "".equals(dstTimeZoneId))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);
		try {
			int diffTime = getDiffTimeZoneRawOffset(dstTimeZoneId);
			Date d = sdf.parse(srcDateTime);
			long nowTime = d.getTime();
			long newNowTime = nowTime - diffTime;
			d = new Date(newNowTime);
			return date2String(dstFormater, d);
		} catch (ParseException e) {
			return null;
		} finally {
			sdf = null;
		}
	}

	/**
	 * 获取系统当前默认时区与UTC的时间差.(单位:毫秒)
	 * 
	 * @return 系统当前默认时区与UTC的时间差.(单位:毫秒)
	 */
	private static int getDefaultTimeZoneRawOffset() {
		return TimeZone.getDefault().getRawOffset();
	}

	/**
	 * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒) 
	 * 
	 * @param timeZoneId 
	 *            时区Id
	 * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
	 */
	private static int getDiffTimeZoneRawOffset(String timeZoneId) {
		return TimeZone.getDefault().getRawOffset()
				- TimeZone.getTimeZone(timeZoneId).getRawOffset();
	}
 
	/**
	 * 将日期时间字符串根据转换为指定时区的日期时间.
	 * 
	 * @param srcDateTime
	 *            待转化的日期时间.
	 * @param dstTimeZoneId
	 *            目标的时区编号.
	 * 
	 * @return 转化后的日期时间.
	 * @see #string2Timezone(String, String, String, String)
	 */
	public static String string2TimezoneDefault(String srcDateTime,
			String dstTimeZoneId) {
		return string2Timezone(RB_DATE_FORMATER, srcDateTime,RB_DATE_FORMATER, dstTimeZoneId);
	}

	/**
	 * 
	 * <p>把long型时间转化为指定时区的时间<p/>
	 * 
	 * @param longDateTime
	 * @param inTimeZoneId
	 * @param outTimeZoneId
	 * @return
	 */
	public static long  longFormatByTimezone(Long longDateTime,String inTimeZoneId ,String outTimeZoneId){
		int   diffTime = getTimeZoneRawOffset(outTimeZoneId,inTimeZoneId);
		Long  newTime = longDateTime + diffTime ;
		return newTime ; 
	}
	/**
	 * 
	 * <p>两个时区的时间差<p/>
	 * 
	 * @param  timeZoneId:任务时区ID
	 * @param  defTimeZoneId：默认时区ID
	 * @return
	 */
	public static int getTimeZoneRawOffset(String  defTimeZoneId, String timeZoneId) {
		if(defTimeZoneId.startsWith("UTC")){
			defTimeZoneId=defTimeZoneId.replace("UTC", "GMT");
		}
		if(timeZoneId.startsWith("UTC")){
			timeZoneId=timeZoneId.replace("UTC", "GMT");
		}
		return TimeZone.getTimeZone(defTimeZoneId).getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();
	}
	/**
	 * 
	 * <p>将long型时间转化为指定格式的字符串时间<p/>
	 * 
	 * @param longDateTime
	 * @param srcFormater
	 * @return
	 */
	public static String parseString(Long longDateTime,String srcFormater){
		Date d = new Date(longDateTime);
		SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);
		return sdf.format(d);
	}
	/**
	 * 
	 * <p>将指定格式的string型转化为long型<p/>
	 * 
	 * @param srcDateTime
	 * @param srcFormater
	 * @return
	 */
	public static Long parseLong(String srcDateTime,String srcFormater){
		Long longtime = null ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);
			Date d = sdf.parse(srcDateTime);
			longtime = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return longtime ; 
	}
	/**
	 * 
	 * <p>指定时区指定格式的时间字符串，转化为指定时区的时间字符串<p/>
	 * 
	 * @param srcDateTime:转前时间字符串
	 * @param srcFormater：转前时间字符串格式
	 * @param timeZoneId：转前时间时区
	 * @param defTimeZoneId：转后时区
	 * @return  返回转后的字符串，格式和转前格式相同
	 */
	public static String dateChangeTimezone(String srcDateTime,String srcFormater,String  timeZoneId, String defTimeZoneId){
		Long longtime = parseLong(srcDateTime,srcFormater);	
		Long retime = longFormatByTimezone(longtime,timeZoneId,defTimeZoneId);
		SimpleDateFormat sdf = new SimpleDateFormat(srcFormater);	
		return sdf.format(new Date(retime));
	}
	/**
	 * 
	 * <p>指定时区指定格式的时间字符串，转化为指定时区的时间字符串<p/>
	 * 
	 * @param srcDateTime:转前时间
	 * @param srcTimeZoneId：转前时间时区
	 * @param formater：转后格式
	 * @param timeZoneId：转后时区
	 * @return
	 */
	public static String dateChangeTimezone(Date srcDateTime,String  srcTimeZoneId,String formater, String timeZoneId){
		Long longtime = srcDateTime.getTime();
		Long retime = longFormatByTimezone(longtime,srcTimeZoneId,timeZoneId);
		SimpleDateFormat sdf = new SimpleDateFormat(formater);	
		return sdf.format(new Date(retime));
	}
	
	/**
	 * 
	 * <p>转换时间为目的时区</p>
	 * 
	 * @param srcDateTime
	 * @param srcTimeZoneId
	 * @param timeZoneId
	 * @return
	 */
	public static Date dateChangeTimezone(Date srcDateTime,String srcTimeZoneId,String timeZoneId){
		Long longtime = srcDateTime.getTime();
		Long retime = longFormatByTimezone(longtime,srcTimeZoneId,timeZoneId);
		return new Date(retime);
	}
	
	/**
	 * 
	 * <p>转换时间为目的时区</p>
	 * 
	 * @param srcDateTime
	 * @param srcTimeZoneId
	 * @param timeZoneId
	 * @return
	 */
	public static Date dateChangeTimezone(String srcDateTime,String srcTimeZoneId,String timeZoneId){
		Long longtime = parseLong(srcDateTime,RB_DATE_FORMATER);	
		Long retime = longFormatByTimezone(longtime,srcTimeZoneId,timeZoneId);
		return new Date(retime);
	}
	/**
	 * 
	 * <p>两个时间 月份进行比较，用d2-d1</p>
	 * 
	 * @param d1 
	 * @param d2
	 * @return
	 */
	public static int compareMonth(Date dateMax,Date dateMin){
		Calendar d1 = Calendar.getInstance();   
        d1.setTime(dateMin);      
        int y1 = d1.get(Calendar.YEAR);
        int m1 = d1.get(Calendar.MONTH);
        
        Calendar d2 = Calendar.getInstance();   
        d2.setTime(dateMax);       
        int y2 = d2.get(Calendar.YEAR);
        int m2 = d2.get(Calendar.MONTH);
        int result = 0;
        if(y1==y2){ 
         result=m2-m1;//两个日期相差几个月，即月份差
        }else{
         result=12*(y2-y1)+m2-m1;//两个日期相差几个月，即月份差
        }
		return result;
	}
	/**
	 * 
	 * <p>将date转为calendar</p>
	 * 
	 * @param d
	 * @return
	 */
	public static Calendar getCalendarByDate(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	/**
	 * 
	 * <p>将制定格式的字符串转化为Date</p>
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date string2Date(String time,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d  = null;
		try {
			d = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 
	 * <p>将制定格式的字符串转化为Date</p>
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date getLastDay(Date date){
		String s = date2String("yyyy-MM",date);		
		return string2Date(s+" 23:59:59",RB_DATE_FORMATER);
	}
	/**
	 * 
	 * <p>获得一个月的最后一天</p>
	 * 
	 * @param date  2012-01-23 11:11:11
	 * @return 2012-01-31 23:59:59
	 */
	public static Date getLastDayOfMonth(Date date) {   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY , cal.getActualMaximum(Calendar.HOUR_OF_DAY ));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
       return cal.getTime();
    } 

	/**
	 * 
	 * <p>获得一个月的第一天</p>
	 * 
	 * @param date  2012-01-23 11:11:11
	 * @return 2012-01-31 23:59:59
	 */
	public static Date getFirstDayOfMonth(Date date) {   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY , cal.getActualMinimum(Calendar.HOUR_OF_DAY ));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
       return cal.getTime();
    } 
	
	/**
	 * 
	 * <p>获得每年的第一天</p>
	 * 
	 * @param date  2012-01-23 11:11:11
	 * @return 2012-01-31 23:59:59
	 */
	public static Date getFirstDayOfYear(Date date) {   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);
        cal.set(Calendar.MONTH,cal.getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY , cal.getActualMinimum(Calendar.HOUR_OF_DAY ));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
       return cal.getTime();
    } 
	
	/**
	 * 
	 * <p>获得每年的最后一天</p>
	 * 
	 * @param date  2012-01-23 11:11:11
	 * @return 2012-01-31 23:59:59
	 */
	public static Date getLastDayOfYear(Date date) {   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(date);
        cal.set(Calendar.MONTH,cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY , cal.getActualMaximum(Calendar.HOUR_OF_DAY ));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
       return cal.getTime();
    } 
	
	public static Long date2long(Date date){
		return date.getTime();
	}
	
	/**
	 * 获取终端传来的时间
	 * @param time
	 * @return
	 */
	public static Date getTsTime(String time){
		logger.info("time: " + time);
		
		SimpleDateFormat f=new SimpleDateFormat(RB_DATE_FORMATER,Locale.CHINA);
		Date date =null;
		try {
			 date =f.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
	
	/**
	 * 获取终端传来的时间
	 * @param time
	 * @return
	 */
	public static Date getRefectTime(String time){
		SimpleDateFormat f=new SimpleDateFormat(SYS_DEF_FORMATER,Locale.ENGLISH);
		Date date =null;
		try {
			 date =f.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 解析时间有String转化为Date
	 * 
	 * @param date
	 * @param pattern
	 *            格式yyyy/MM/dd 或其他
	 * @return
	 */
	public static Date parseDate(String date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(date);
		} catch (ParseException e) {
			// ======
		}
		return null;
	}
	
	/**
	 * 获取指定时间最后的时间
	 * @param date
	 * @return
	 */
	public static Date getThisLastTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY,
				cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 * 获得上个月的今天
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
	/**
	 * 结束时间-起始时间返回小时数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Long date1SubDate2(Date beginDate,Date endDate){
		Long beginDateLong = beginDate.getTime();
		Long endDateLong = endDate.getTime();
		Long hours = (endDateLong-beginDateLong)/1000/60/60;
		return hours;
		
	} 
	
	/**
	 * 结束时间-起始时间返回小时数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Long date1SubDate2Minute(Date beginDate,Date endDate){
		Long beginDateLong = beginDate.getTime();
		Long endDateLong = endDate.getTime();
		Long hours = (endDateLong-beginDateLong)/1000/60;
		return hours;
		
	} 

	
	/**
	 * 结束时间-起始时间返回小时数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Long date1SubDate2(String beginDate,String endDate){
		Long beginDateLong = string2Date(beginDate, "yyyy-MM-dd HH:mm:ss").getTime();
		Long endDateLong = string2Date(endDate, "yyyy-MM-dd HH:mm:ss").getTime();
		Long hours = (endDateLong-beginDateLong)/1000/60/60;
		return hours;
		
	}
	
	public static Date getTodayEarly(){
		Date date = new Date();
		String dateStr = date2String("yyyy-MM-dd", date)+" 00:00:00";
		return string2Date(dateStr, "yyyy-MM-dd HH:mm:ss");
		
	}


}
