package com.team.vo.stat;

import java.io.Serializable;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午11:02 2018/8/16
 */
public class TerminalCost implements Serializable {

    private Integer tsid;

    private Double flow;

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }
}
