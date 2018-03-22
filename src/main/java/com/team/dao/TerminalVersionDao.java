package com.team.dao;

import com.team.model.TerminalVersion;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午6:19 2018/3/20
 */
public interface TerminalVersionDao {

    List<TerminalVersion> list();

    int save(TerminalVersion terminalVersion);

    int update(TerminalVersion terminalVersion);
}
