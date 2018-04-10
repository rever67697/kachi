package com.team.util;

/**
 * 常量
 * @author Administrator
 *
 */
public class MConstant {
	
	public static final String CONNECTOR = "_";
	
	/**
	 * 卡分组缓存
	 */
	public static final String MEM_SIM_GROUP = "simGroupPool";
	
	
	/**
	 * 卡缓存
	 */
	public static final String MEM_SIM = "simPool";
	public static final String CACHE_SIM_KEY_PREF = "SIM_"; //卡 ,"SIM_" + imsi
	public static final String CACHE_PACKAGE_KEY_PREF = "PACKAGE_"; //套餐，"PACKAGE_" + ID

	/**
	 * 卡当前月流量缓存
	 */
	public static final String MEM_SIM_FlOW = "simFlowPool";
	public static final String CACHE_FLOW_KEY_PREF = "FLOW_"; //月流量, "FLOW_" + imsi
	public static final String CACHE_FLOW_DAY_KEY_PREE = "FLOW_DAY_"; //日流量, "FLOW_DAY_" + imsi
	public static final String CACHE_HEARTBAE_PREE = "HEART_"; //最后一次心跳缓存，"HEART_" + tsid + imsi;
	public static final String CACHE_COSTDAY_PREF = "COSTDAY_"; //设备日流量记录，"COSTDAY_" + tsid + "_" + isLocal;
	
	
	/**
	 * 公共数据缓存
	 */
	public static final String MEM_PUBLIC = "publicPool";
	
	public static final String CACHE_COUNTRY_KEY_PREE = "COUNTRY_"; //国家，"COUNTRY_" + countryCode,未使用
	public static final String CACHE_OPERATOR_KEY_PREE = "OPERATOR_"; //运营商，"OPERATOR_" + operatorCode
	public static final String CACHE_COUNTRY2MCC_KEY_PREE = "COUNTRY2MCC_"; //国家与MCC对照，"COUNTRY2MCC_"  + countryCode
	public static final String CACHE_MCC2COUNTRY_KEY_PREE = "MCC2COUNTRY_"; //MCC与国家对照，"MCC2COUNTRY_" + MCC
	public static final String CACHE_PREFER_KEY_PREE = "PREFER_";  //运营商优选关系，"PREFER_" + operatorCode
	public static final String CACHE_LACPROVINCE_KEY_PREE = "LACPROVINCE_"; //LAC与省对照关系，"LACPROVINCE_" + operatorCode + "_" + lacPref
	public static final String CACHE_SUM_PREF = "SUM_"; //卡池缓存，"SUM_" + cpId;
	public static final String CACHE_SPM_PREF = "SPM_";  //卡池管理服务，以MAC作为KEY
	public static final String CACHE_CON_OPERATOR_KEY_PREE = "CON_OPERATOR_"; //国家下有卡运营商的缓存
	public static final String CACHE_CON_ROAM_KEY_PREE = "CON_ROAM_"; //国家支持漫游运营商列表
	public static final String CACHE_OPERATOR_GROUP_KEY_PREE = "OPERATOR_GROUPKEY_"; //运营商的所有SIM分组KEY
	public static final String CACHE_ORDER_TRIP_KEY_PREE = "ORDER_TRIP_"; //订单行程，以终端ID和日期为作KEY，缓存有效期24小时
	public static final String CACHE_COUNTRY_PRICE_KEY_PREE = "PRICE_"; //价格，以国家和customerId为KEY


	//选卡时最小流量，小于50MB的卡不再使用
	public static int CHOOSE_MIN_FLOW = 50*1024;

	//基准时区
	public static String CHINA_TIMEZONE = "GMT+8";
	
	//终端默认超时时间，如果终端超过180分钟没有收到心跳，云端将释放终端占用的SIM卡
	public static int TERMINAL_OVER_TIME = 60;
	
	
	public static final int A15101_OK = 0;
	public static final int A15101_NO_CARD =1;
	public static final int A15101_NO_BALANCE = 2;
	public static final int A15101_NO_ORDER = 3;
	public static final int A15101_TS_ERROR = 4;
	
	public static int HEARTBEAT_INIT = 0;
	public static int HEARTBEAT_NORMAL =1;
	public static int HEARTBEAT_SUPPLY =2;
	
	
	/**
	 *卡池状态，活动
	 */
	public static final int ISACTIVE=1;
	/**
	 *卡池状态，非活动，不在线
	 */
	public static final int NOACTIVE=0;

	

	
//	//卡状态0：正常
//	public static final int STATUS_OK = 0;
//	
//	//卡状态1：停用
//	public static final int STATUS_STOP = 1;
//	
//	//卡状态2：指定
//	public static final int STATUS_DELETE = 2;
//	
//	//卡状态3：待激活
//	public static final int STATUS_UNSTART = 3;
//	
//	//卡状态4：作废
//	public static final int STATUS_CANCEL = 4;
//	
//	//卡状态5：待测试
//	public static final int STATUS_TOBETESTED = 5;
//	
//	//卡状态6：测试成功
//	public static final int STATUS_PASSED = 6;
//	
//	//卡状态7：测试失败
//	public static final int STATUS_FAIL = 7;

	//卡池上报状态
	/**
	 * 表示未插卡
	 */
	public static final String CHANNELNOCARD = "00"; 
	
	/**
	 * 表示插卡未启动
	 */
	public static final String CHANNELNOSTART = "55"; 
	
	/**
	 * 表示插卡工作中
	 */
	public static final String CHANNELSTART = "AA"; 
	
	/**
	 * 表示异常
	 */
	public static final String CHANNELERROR = "FF"; 


}
