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
	
	/**
	 * 卡当前月流量缓存
	 */
	public static final String MEM_SIM_FlOW = "simFlowPool";
	
	
	/**
	 * 公共数据缓存
	 */
	public static final String MEM_PUBLIC = "publicPool";
	
	
	/**
	 * 缓存KEY前缀
	 */
	public static final String CACHE_SIM_KEY_PREF = "SIM_"; //卡 ,"SIM_" + imsi
	public static final String CACHE_FLOW_KEY_PREF = "FLOW_"; //月流量, "FLOW_" + imsi
	public static final String CACHE_FLOW_DAY_KEY_PREE = "FLOW_DAY_"; //日流量, "FLOW_DAY_" + imsi
	public static final String CACHE_PACKAGE_KEY_PREF = "PACKAGE_"; //套餐，"PACKAGE_" + ID
	public static final String CACHE_COUNTRY_KEY_PREE = "COUNTRY_"; //国家，"COUNTRY_" + countryCode
	public static final String CACHE_OPERATOR_KEY_PREE = "OPERATOR_"; //运营商，"OPERATOR_" + operatorCode
	public static final String CACHE_COUNTRY2MCC_KEY_PREE = "COUNTRY2MCC_"; //国家与MCC对照，"COUNTRY2MCC_"  + countryCode
	public static final String CACHE_MCC2COUNTRY_KEY_PREE = "MCC2COUNTRY_"; //MCC与国家对照，"MCC2COUNTRY_" + MCC
	public static final String CACHE_PREFER_KEY_PREE = "PREFER_";  //运营商优选关系，"PREFER_" + operatorCode
	public static final String CACHE_LACPROVINCE_KEY_PREE = "LACPROVINCE_"; //LAC与省对照关系，"LACPROVINCE_" + operatorCode + "_" + lacPref
	public static final String CACHE_HEARTBAE_PREE = "HEART_"; //最后一次心跳缓存，"HEART_" + tsid + imsi;
	public static final String CACHE_COSTDAY_PREF = "COSTDAY_"; //设备日流量记录，"COSTDAY_" + tsid + "_" + isLocal;
	public static final String CACHE_SUM_PREF = "SUM_"; //卡池缓存，"SUM_" + cpId;
	public static final String CACHE_SPM_PREF = "SPM_";  //卡池管理服务，以MAC作为KEY

	//选卡时最小流量，小于50MB的卡不再使用
	public static int CHOOSE_MIN_FLOW = 50*1024;

	//基准时区
	public static String CHINA_TIMEZONE = "GMT+8";
	
	
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

	
	//-------------卡池状态--------------------------------------------------------------------------------------------------
	
	/**
	 * 卡池的状态  0：正常  ;
	 */
	public static final int CARD_NORMAL=0;
	/** 
	 * 卡池的状态  1：无信息 ; (缺少套餐，pin码，imsi号)
	 */
	public static final int CARD_NOINFO=1;
	/**
	 * 卡池的状态  2：拔出状态 ; 
	 */
	public static final int CARD_BAD=2;
	
	/**
	 * 卡池的状态  2：拔出状态 ; 
	 */
	public static final int CARD_No=3;
	
	
	/**
	 * 卡池的状态  3：底层芯片掉卡; 
	 */
	public static final int CARD_LOSE=3;
	
	/**
	 * 卡池的状态  4：底层芯片故障; 
	 */
	public static final int CARD_FAULT=4;
	
	/**
	 * 卡池的状态  4：底层芯片空闲; 
	 */
	public static final int CARD_IDLE=5;
	
	/**
	 * 卡池的状态  4：底层芯片初始化; 
	 */
	public static final int CARD_INIT=6;
	/**
	 * 卡池的状态  4：卡返回06016 表示暂时不可用; 
	 */
	public static final int CARD_06015=7;
	
	/**
	 * 卡池的状态  8：卡超时; 
	 */
	public static final int CARD_TIMEOUT=8;

	
	//卡状态0：正常
	public static final int STATUS_OK = 0;
	
	//卡状态1：停用
	public static final int STATUS_STOP = 1;
	
	//卡状态2：指定
	public static final int STATUS_DELETE = 2;
	
	//卡状态3：待激活
	public static final int STATUS_UNSTART = 3;
	
	//卡状态4：作废
	public static final int STATUS_CANCEL = 4;
	
	//卡状态5：待测试
	public static final int STATUS_TOBETESTED = 5;
	
	//卡状态6：测试成功
	public static final int STATUS_PASSED = 6;
	
	//卡状态7：测试失败
	public static final int STATUS_FAIL = 7;

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
