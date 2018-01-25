package com.team.aop;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.team.util.CommonUtil;
import com.team.util.IConstant;

/**
 * 业务拦截权限和记录日志
 * 创建日期：2018-1-23上午12:56:13
 * author:wuzhiheng
 */
@Aspect
@Component
public class PermissionLogAop {

	@Pointcut(value="@annotation(com.team.aop.PermissionLog)")
	public void cutService(){
		
	}
	
	@Around("cutService()")
	public Object excuteLog(ProceedingJoinPoint point) throws Throwable{
		MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        PermissionLog logInfo = method.getAnnotation(PermissionLog.class);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        
		//1.执行方法之前，判断权限--onlyLog为true则代表这个只执行记录日志，不过滤权限，默认是false
        Map<String, Object> permission = CommonUtil.getUserPermission(request);
        if(!logInfo.onlyLog()){
    		boolean ok = false;
    		for (Entry<String, Object> entry : permission.entrySet()) {
    			if(method.getName().equals(entry.getKey())){
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
		
		//3.记录日志
		PermissionLog bussiness = point.getTarget().getClass().getAnnotation(PermissionLog.class);
		if(bussiness==null || "".equals(bussiness.value())){
			throw new Exception("记录日志需要在这个类上标志这个类的业务");
		}
		
		StringBuilder message = new StringBuilder(bussiness.value()).append("-->");
		
		//注解在方法上，如果value没有值，那么在对应的权限里面找，注意，这种情况主要请求的路径和方法名一致
		if("".equals(logInfo.value())){
			String name = "";
			for (Entry<String, Object> entry : permission.entrySet()) {
				if(method.getName().equals(entry.getKey())){
					name = entry.getValue().toString();
					break;
				}
			}
			
			if((name.indexOf("添加") > -1 && !CommonUtil.StringIsNull(request.getParameter("id")))){
				name = name.replace("添加", "修改");
			}else if(name.indexOf("修改") > -1 && CommonUtil.StringIsNull(request.getParameter("id"))){
				name = name.replace("修改", "添加");
			}
			
			message.append(name);
		}else{
			message.append(logInfo.value());
		}
		
		//记录日志的额外信息，一般是指主要的参数是什么，key的格式的成对的key用‘_’分割，多个key用‘;’分割
		if(!"".equals(logInfo.key())){
			String[] Keygroup = logInfo.key().split(";");
			message.append("(");
			for (String s : Keygroup) {
				String[] key = s.split("_");
				if(key.length==2)
				message.append(key[1]).append("=").append(request.getParameter(key[0])).append(" | ");
			}
			message.append(")");
		}
		
		System.out.println(message.toString());
		
		return result;
	}
}
