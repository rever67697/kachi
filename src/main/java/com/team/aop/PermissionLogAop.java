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
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//1.执行方法之前，判断权限
		Map<String, Object> permission = CommonUtil.getUserPermission(request);
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

		//2.执行目标方法
		Object result = point.proceed();
		
		//3.记录日志
		PermissionLog info = method.getAnnotation(PermissionLog.class);
		PermissionLog p = point.getTarget().getClass().getAnnotation(PermissionLog.class);
		if(p==null || "".equals(p.value())){
			throw new Exception("记录日志需要在这个类上标志这个类的业务");
		}
		StringBuilder message = new StringBuilder(p.value());
		for (Entry<String, Object> entry : permission.entrySet()) {
			if(method.getName().equals(entry.getKey())){
				message.append("-->").append(entry.getValue());
				if(!"".equals(info.key())){
					String[] Keygroup = info.key().split(";");
					message.append("(");
					for (String s : Keygroup) {
						String[] key = s.split("_");
						if(key.length==2)
						message.append(key[1]).append("=").append(request.getParameter(key[0]));
					}
					message.append(")");
				}
				break;
			}
		}
		System.out.println(message.toString());
		return result;
	}
}
