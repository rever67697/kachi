package com.team.vo.stat;

import java.io.Serializable;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午9:19 2018/8/16
 */
public class TerminalCount implements Serializable {

    private String statDate;

    private Integer activeNum;

    private Integer totalNum;

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public Integer getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(Integer activeNum) {
        this.activeNum = activeNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
