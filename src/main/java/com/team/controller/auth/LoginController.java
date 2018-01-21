package com.team.controller.auth;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthRoleService;
import com.team.service.auth.TbAuthPermissionService;
import com.team.service.auth.TbAuthUserService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;



/**
 * 创建日期：2017-12-18下午1:01:56
 * author:wuzhiheng
 */
@Controller
public class LoginController {
	
	@Autowired
	private TbAuthRoleService tbAuthRoleService;
	
	@Autowired
	private TbAuthUserService tbAuthUserService;
	
	@Autowired
	private TbAuthPermissionService tbAuthPermissionService;
	
	@PostMapping("/login")
	@ResponseBody
	public ReturnMsg  login(String userName,String passWord,String code,HttpServletRequest request,
			HttpServletResponse response){
		ReturnMsg returnMsg = null;
		String msg = "";
		String verificationCode = (String) request.getSession().getAttribute("verificationCode");
		if(verificationCode != null && verificationCode.equals(code)){
			//根据用户名查询用户实体
			TbAuthUser user = tbAuthUserService.getUserByName(userName);
			if(CommonUtil.validateUser(user,passWord)){
				//验证通过
				List<TbAuthRole> roles = tbAuthRoleService.getRolesByUser(user);
				user.setRoles(roles);
				request.getSession().setAttribute(IConstant.SESSION_USER_NAME, user);
				Cookie cookie = new Cookie(IConstant.SESSION_USER_NAME,user.getName());
				cookie.setMaxAge(60*60*24*7);//7天有效
				response.addCookie(cookie);
			}else{
				msg = "用户名或密码错误！";
			}
		}else{
			msg = "验证码错误！";
		}
		if(CommonUtil.StringIsNull(msg)){
			returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		}else{
			returnMsg = IConstant.MSG_OPERATE_ERROR;
			returnMsg.setMsg(msg);
		}
		return returnMsg;
	}
	
	@PostMapping("/getMenu")
	@ResponseBody
	public Object getMenu(HttpServletRequest request){
		TbAuthUser user = getUser(request);
		return tbAuthPermissionService.getMenuByUser(user);
	}
	
	
	@PostMapping("/getPermissionTree")
	@ResponseBody
	public Object getPermissionTree(){
		return tbAuthPermissionService.getPermissionTree();
	}
	
	
	@PostMapping("/getUser")
	@ResponseBody
	public TbAuthUser getUser(HttpServletRequest request){
		return (TbAuthUser) request.getSession().getAttribute(IConstant.SESSION_USER_NAME);
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public ReturnMsg logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(IConstant.SESSION_USER_NAME);
		Cookie cookie = new Cookie(IConstant.SESSION_USER_NAME,"");
		cookie.setMaxAge(0);//消除cookie
		response.addCookie(cookie);
		return IConstant.MSG_OPERATE_SUCCESS;
	}
	
	@PostMapping("/getFunctions")
	@ResponseBody
	public ReturnMsg getFunctions(HttpServletRequest request,Integer id){
		return tbAuthPermissionService.getFunByUser(getUser(request),id);
	}
	
}
