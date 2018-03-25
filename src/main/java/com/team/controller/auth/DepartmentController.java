package com.team.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.model.auth.Department;
import com.team.service.auth.DepartmentService;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-30下午5:57:37
 * author:wuzhiheng
 */
@RestController
@PermissionLog("部门管理")
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/save")
	@PermissionLog(value="维护部门",key="id_id;text_名称",onlyLog=true)
	public ReturnMsg savetDepartment(Integer id,Integer parentId,String text,String url,String funDesc){
		Department department = new Department();
		department.setId(id);
		department.setParentId(parentId);
		department.setName(text);
		department.setAbbr(url);
		department.setNote(funDesc);
		return departmentService.saveOrUpdate(department);
	}
	
	@PostMapping("/delete")
	@PermissionLog(value="删除部门",key="name_名称",onlyLog=true)
	public ReturnMsg deleteDepartment(Integer id){
		return departmentService.delete(id);
	}
	
	@PostMapping("/list")
	public Object getDepartmentList(){
		return departmentService.list();
	}
	
}
