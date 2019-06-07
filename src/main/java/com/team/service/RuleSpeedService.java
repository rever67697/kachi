package com.team.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.RuleSpeedDao;
import com.team.model.RuleSpeed;
import com.team.service.impl.BaseService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 15:52 2019-06-06
 */
@Service
public class RuleSpeedService extends BaseService {

    @Autowired
    private RuleSpeedDao ruleSpeedDao;

    public ResultList list(Integer departmentId,int page,int rows){

        PageHelper.startPage(page,rows);

        Map<String,Object> map = new HashMap<>();
        map.put("dId",getDId());
        map.put("departmentId",departmentId);
        List<RuleSpeed> list = ruleSpeedDao.list(map);

        PageInfo<RuleSpeed> ruleSpeedPage = new PageInfo<RuleSpeed>(list);

        return new ResultList(ruleSpeedPage.getTotal(),list);
    }

    public ReturnMsg saveOrUpdate(RuleSpeed ruleSpeed){
        if(ruleSpeed.getId() == null){
            int count = ruleSpeedDao.save(ruleSpeed);
            if(count < 1 )
                return errorTip("添加失败,一个部门只能创建一条数据");
        }else
            ruleSpeedDao.update(ruleSpeed);
        return successTip();
    }


    public ReturnMsg delete(Integer id){
        ruleSpeedDao.delete(id);
        return successTip();
    }

}
