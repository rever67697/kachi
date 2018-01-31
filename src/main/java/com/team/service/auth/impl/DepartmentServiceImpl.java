package com.team.service.auth.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.auth.DepartmentDao;
import com.team.model.auth.Department;
import com.team.model.auth.TbAuthPermission;
import com.team.service.auth.DepartmentService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-30下午5:50:36
 * author:wuzhiheng
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<TbAuthPermission> list() {
		List<TbAuthPermission> list = departmentDao.list();
		return  CommonUtil.bulidTree(list,true);
	}

	@Override
	public ReturnMsg saveOrUpdate(Department d) {
		if(d.getId()!=null){
			departmentDao.update(d);
		}else{
			departmentDao.insert(d);
		}
		return IConstant.MSG_OPERATE_SUCCESS;
	}

	@Override
	public ReturnMsg delete(Integer id) {
		int count = departmentDao.delete(id);
		return count>0?IConstant.MSG_OPERATE_SUCCESS:IConstant.MSG_OPERATE_ERROR;
	}

}
