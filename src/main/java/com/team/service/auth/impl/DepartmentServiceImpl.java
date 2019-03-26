package com.team.service.auth.impl;

import java.util.List;

import com.team.dao.auth.DepartmentIpConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.auth.DepartmentDao;
import com.team.model.auth.Department;
import com.team.model.auth.TbAuthPermission;
import com.team.service.auth.DepartmentService;
import com.team.service.impl.BaseService;
import com.team.util.CommonUtil;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-30下午5:50:36
 * author:wuzhiheng
 */
@Service
public class DepartmentServiceImpl extends BaseService implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DepartmentIpConfigDao departmentIpConfigDao;
	
	@Override
	public List<TbAuthPermission> list() {
		Integer dId = getDId();
		List<TbAuthPermission> list = departmentDao.list(dId);
		return  CommonUtil.bulidTree(list,true);
	}

	@Override
	public ReturnMsg saveOrUpdate(Department d) {
		if(d.getId()!=null){
			departmentDao.update(d);
		}else{
			departmentDao.insert(d);
		}

		//保存限制登录的ip 并且只有admin的权限才能修改 departmentId=0
		if(d.getParentId() == null && getDepartmentId() == 0){
			int count = departmentIpConfigDao.update(d);
			if(count == 0 )
				departmentIpConfigDao.save(d);
		}

		return super.successTip();
	}

	@Override
	public ReturnMsg delete(Integer id) {
		int count = departmentDao.delete(id);
		return count>0?super.successTip():super.errorTip();
	}

}
