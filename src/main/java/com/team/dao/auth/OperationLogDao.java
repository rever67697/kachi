package com.team.dao.auth;

import java.util.List;
import java.util.Map;


import com.team.model.auth.OperationLog;

/**
 * 创建日期：2018-1-26下午10:33:08
 * author:wuzhiheng
 */
public interface OperationLogDao {

	public int saveLog(OperationLog log);
	
	public List<OperationLog> getLogList(Map<String, Object> map);
}
