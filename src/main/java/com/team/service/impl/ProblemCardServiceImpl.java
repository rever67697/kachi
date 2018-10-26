package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.ProblemCardDao;
import com.team.model.ProblemCard;
import com.team.service.ProblemCardService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:06 PM 2018/10/25
 */
@Service
public class ProblemCardServiceImpl extends BaseService implements ProblemCardService {

    @Autowired
    private ProblemCardDao problemCardDao;

    @Override
    public ResultList list(Date startDate,Date endDate,Integer tsid,Long imsi,int page, int rows) {
        PageHelper.startPage(page, rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        map.put("imsi",imsi);
        List<ProblemCard> problemCardList = problemCardDao.list(map);
        PageInfo<ProblemCard> pageInfo = new PageInfo<ProblemCard>(problemCardList);
        return new ResultList(pageInfo.getTotal(), problemCardList);
    }

    @Override
    public ReturnMsg delete(Long imsi) {
        int count = problemCardDao.delete(imsi);
        return count>0?successTip():errorTip();
    }
}
