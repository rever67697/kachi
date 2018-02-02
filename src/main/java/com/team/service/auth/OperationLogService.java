package com.team.service.auth;



import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-29下午5:01:45
 * author:wuzhiheng
 */
public interface OperationLogService {

	public ResultList getLogList(String username,String bussinesstype,int page,int rows);
}
