package com.team.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.exception.KachiException;
import com.team.util.IConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.team.model.auth.TbAuthUser;
import com.team.util.CommonUtil;

/**
 * 创建日期：2017-12-14下午4:30:18
 * author:wuzhiheng
 */
public class LoginFilter implements Filter{

	private String noFilterPath;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//System.out.println(request.getRequestURI());
		
		//先判断用户有没有登录
		TbAuthUser user = CommonUtil.getUser(request);
		
		if(user == null){
			boolean ok = false;
			for (String string : noFilterPath.split(";")) {
				if(request.getRequestURI().endsWith(string)){
					ok = true;
					break;
				}
			}
			if(ok){
				arg2.doFilter(arg0, arg1);
			}else{
				/*if(request.getCookies()!=null)
				for (Cookie cookie : request.getCookies()) {
					if(IConstant.SESSION_USER_NAME.equals(cookie.getName())){
						user = new TbAuthUser();
						user.setUserName("admin");
						request.getSession().setAttribute(IConstant.SESSION_USER_NAME, user);
						arg2.doFilter(arg0, arg1);
						return;
					}
				}*/
				response.sendRedirect(request.getContextPath()+"/site/login.html");
			}
		}else{
			validPath(request);
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.noFilterPath = arg0.getInitParameter("noFilterPath");
	}

	//过滤html
	private boolean validPath(HttpServletRequest request){

		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		if(!uri.endsWith("html"))
			return true;

		Map<String, Object> userPermission = CommonUtil.getUserPermission(request);

		boolean ok = false;
		for (Map.Entry<String, Object> entry : userPermission.entrySet()) {
			if(uri.matches(".*"+entry.getKey()+"$")){
				ok=true;
				break;
			}
		}
		if(!ok){
			throw new KachiException(IConstant.NO_PERMISSION);
		}
		return true;
	}

}
