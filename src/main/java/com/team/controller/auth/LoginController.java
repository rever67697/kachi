package com.team.controller.auth;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.exception.KachiException;
import com.team.annotation.PermissionLog;
import com.team.model.auth.TbAuthPermission;
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
@PermissionLog("登录信息")
public class LoginController {
	
	@Autowired
	private TbAuthRoleService tbAuthRoleService;
	
	@Autowired
	private TbAuthUserService tbAuthUserService;
	
	@Autowired
	private TbAuthPermissionService tbAuthPermissionService;
	
	@PermissionLog(value="用户登录",key="userName_用户名",onlyLog=true)
	@PostMapping("/login")
	@ResponseBody
	public ReturnMsg  login(String userName,String passWord,String code,HttpServletRequest request,
			HttpServletResponse response){
		String msg = "";
		String verificationCode = (String) request.getSession().getAttribute(IConstant.VERIFICATIONCODE);
		if(verificationCode != null && verificationCode.equals(code)){
			//根据用户名查询用户实体
			TbAuthUser user = tbAuthUserService.getUserByName(userName);
			if(CommonUtil.validateUser(user,passWord)){
				//验证通过
				List<TbAuthRole> roles = tbAuthRoleService.getRolesByUser(user);
				user.setRoles(roles);
				handlePermission(user.getId(),request);
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
		if(!CommonUtil.StringIsNull(msg)){
			throw new KachiException(msg);
		}
		return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS);
	}

	@GetMapping({"/index","/"})
	public String index(Model model){
		model.addAttribute("name","wzh");
		return "index";
	}

	@PostMapping("/getMenu")
	@ResponseBody
	/**
	 *权限管理，获取index页面的左侧菜单
	 * @param request
	 * @return
	 */
	public Object getMenu(HttpServletRequest request){
		TbAuthUser user = getUser(request);
		return tbAuthPermissionService.getMenuByUser(user);
	}
	
	
	@PostMapping("/getPermissionTree")
	@ResponseBody
	/**
	 * 构建菜单树
	 */
	public Object getPermissionTree(){
		return tbAuthPermissionService.getPermissionTree();
	}
	
	
	@PostMapping("/getUser")
	@ResponseBody
	public TbAuthUser getUser(HttpServletRequest request){
		return (TbAuthUser) request.getSession().getAttribute(IConstant.SESSION_USER_NAME);
	}

	@GetMapping("/logout")
	@PermissionLog(value="退出登录",onlyLog=true)
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute(IConstant.SESSION_USER_NAME);
		Cookie cookie = new Cookie(IConstant.SESSION_USER_NAME,"");
		cookie.setMaxAge(0);//消除cookie
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath()+"/site/login.html");
		//return new ReturnMsg(IConstant.CODE_SUCCESS, IConstant.MSG_SUCCESS);
	}
	
	@PostMapping("/getFunctions")
	@ResponseBody
	/**
	 * 权限管理，每跳到一个页面寻找功能
	 */
	public ReturnMsg getFunctions(HttpServletRequest request,Integer id){
		return tbAuthPermissionService.getFunByUser(getUser(request),id);
	}
	
	private void handlePermission(Integer userId,HttpServletRequest request) {
		List<TbAuthPermission> permission = tbAuthPermissionService.getPermissionByUser(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(CommonUtil.listNotBlank(permission)){
			for (TbAuthPermission p : permission) {
				if(p.getUrl()!=null&&!p.getUrl().equals(""))
				map.put(p.getUrl(), p.getText());
			}
		}
		request.getSession().setAttribute(IConstant.SESSION_PERMISSION_MAP, map);
	}
}
