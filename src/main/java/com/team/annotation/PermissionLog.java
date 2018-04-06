package com.team.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建日期：2018-1-23上午12:52:44
 * author:wuzhiheng
 * 
 * 1.该注解用于Controller的类上：
 * 	用于标志这个类是什么业务，用于记录日志时的拼接
 * 2.该注解主要用于方法上，
 * 	用于判断用户权限和执行方法后的记录日志,在这里要求方法名和过滤的url一致，方便判断
 * 
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionLog {
	String value() default "";

	//以;分隔组，组内以_分隔，例如->"id_ID;name_姓名"
	String key() default "";
	
	boolean onlyLog() default false;
}
