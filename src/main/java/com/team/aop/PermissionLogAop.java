package com.team.aop;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.team.annotation.PermissionLog;
import com.team.exception.KachiException;
import com.team.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team.dao.auth.OperationLogDao;
import com.team.model.auth.OperationLog;
import com.team.model.auth.TbAuthUser;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.util.LogManager;

/**
 * 业务拦截权限和记录日志
 * 创建日期：2018-1-23上午12:56:13
 * author:wuzhiheng
 */
@Aspect
@Component
public class PermissionLogAop {
	
	@Autowired
	private OperationLogDao operationLogDao;

	@Pointcut(value="@annotation(com.team.annotation.PermissionLog)")
	public void cutService(){
		
	}
	
	@Around("cutService()")
	public Object excuteLog(ProceedingJoinPoint point) throws Throwable{

		MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        PermissionLog logInfo = method.getAnnotation(PermissionLog.class);
        HttpServletRequest request = CommonUtil.getRequest();
		Map<String, Object> permission = CommonUtil.getUserPermission(request);

		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		//1.执行方法之前，判断权限--onlyLog为true则代表这个只执行记录日志，不过滤权限，默认是false
        if(!logInfo.onlyLog()){
    		boolean ok = false;
    		for (Entry<String, Object> entry : permission.entrySet()) {
    			if(uri.matches(entry.getKey())){
					ok=true;
    				break;
				}
    		}
    		if(!ok){
    			throw new KachiException(IConstant.NO_PERMISSION);
    		}
        }

		//2.执行目标方法
		Object result = point.proceed();
		
		//3.记录日志，如果用户为空则不记录
		TbAuthUser user = CommonUtil.getUser(request);
		if(user!=null){
			PermissionLog bussiness = point.getTarget().getClass().getAnnotation(PermissionLog.class);
			String bussinesstype = bussiness.value();
			if(bussiness==null || "".equals(bussinesstype)){
				throw new RuntimeException("记录日志需要在这个类上标志这个类的业务");
			}

			String operation = "";
			//注解在方法上，如果value没有值，那么在对应的权限里面找，注意，这种情况主要请求的路径和方法名一致
			if("".equals(logInfo.value())){
				for (Entry<String, Object> entry : permission.entrySet()) {
					if(uri.matches(entry.getKey())){
						operation = entry.getValue().toString();
						break;
					}
				}
				
				if((operation.indexOf("添加") > -1 && !CommonUtil.StringIsNull(request.getParameter("id")))){
					operation = operation.replace("添加", "修改");
				}else if(operation.indexOf("修改") > -1 && CommonUtil.StringIsNull(request.getParameter("id"))){
					operation = operation.replace("修改", "添加");
				}
				
			}else{
				operation = logInfo.value();
			}
			
			//记录日志的额外信息，一般是指主要的参数是什么，key的格式的成对的key用‘_’分割，多个key用‘;’分割
//			StringBuilder desc = new StringBuilder();
//			String desc_str = "";
//			if(!"".equals(logInfo.key())){
//				String[] Keygroup = logInfo.key().split(";");
//				for (String s : Keygroup) {
//					String[] key = s.split("_");
//					if(key.length==2)
//						desc.append(key[1]).append("=").append(request.getParameter(key[0])).append(" | ");
//				}
//				desc_str = desc.substring(0, desc.lastIndexOf(" | "));
//			}
			
			//下面是正式开启一个异步的任务来执行这个记录日志
			final OperationLog operationLog = new OperationLog(user.getName(), bussinesstype, operation, getParamURL(request),user.getDepartmentId(), IPUtils.getIpAddr(request));
			LogManager.me().executeLog(new TimerTask() {
				@Override
				public void run() {
					operationLogDao.saveLog(operationLog);
				}
			});
		}
		
		return result;
	}

	public static String getParamURL(HttpServletRequest request){
		java.util.Enumeration params = request.getParameterNames();
		StringBuffer req_pram = new StringBuffer();

		while (params.hasMoreElements()) {
			String paramName=(String) (params.nextElement());
			try {
				if(CommonUtil.StringIsNull(request.getParameter(paramName))
						|| "passWord".equals(paramName)
						|| "repeatPwd".equals(paramName)){
					continue;
				}
				req_pram.append(paramName+"="+ URLDecoder.decode(request.getParameter(paramName),"utf-8")+"&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String desc = req_pram.toString();
		return desc.lastIndexOf("&")>-1?desc.substring(0,desc.lastIndexOf("&")):desc;
	}

	public static void main(String[] args){
		System.out.println("/simcard/getByPool".matches("simcard/getByPool"));
	}
}
