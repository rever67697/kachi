package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalVersionDao;
import com.team.model.ChannelCard;
import com.team.model.TerminalVersion;
import com.team.service.TerminalVersionService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午6:21 2018/3/20
 */
@Service
@Transactional
public class TerminalVersionServiceImpl extends  BaseService implements TerminalVersionService {

    @Autowired
    private TerminalVersionDao terminalVersionDao;
    @Value("${urlPrefix}")
    private String urlPrefix;

    @Override
    public ResultList list(int page,int rows) {
        PageHelper.startPage(page, rows);

        List<TerminalVersion> list = terminalVersionDao.list();
        PageInfo<TerminalVersion> pageInfo = new PageInfo<TerminalVersion>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }

    @Override
    public ReturnMsg saveOrUpdate(TerminalVersion terminalVersion,MultipartFile file) throws Exception{
        System.out.println(file.getOriginalFilename());

        int count = 0;
        //指定升级的终端列表不能超多200个
        if(!CommonUtil.StringIsNull(terminalVersion.getTerminalList())
                && terminalVersion.getTerminalList().split(",").length>200){
            return super.errorTip("终端列表不能超过两百个!");
        }

        if(terminalVersion.getId()!=null){
            count = terminalVersionDao.update(terminalVersion);
        }else{
            count = terminalVersionDao.save(terminalVersion);
        }
        if(!CommonUtil.StringIsNull(file.getOriginalFilename())){
            File parentFile = new File("kachi/file");
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }

            String fileName = terminalVersion.getId()+"-"+System.currentTimeMillis()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            FileCopyUtils.copy(file.getBytes(),new File(parentFile,fileName));

            terminalVersion.setDownUrl(urlPrefix+fileName);
            terminalVersionDao.update(terminalVersion);
        }

        return count>0?super.successTip():super.errorTip();
    }

    @Override
    public ReturnMsg delete(String ids) {
        int count = 0;

        List<Integer> idList = CommonUtil.getIntList(ids);

        count = terminalVersionDao.delete(idList);

        return count >0 ?super.successTip():super.errorTip();
    }

}
