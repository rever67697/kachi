package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 统计在线终端
 * @Date Created in 16:20 2019-03-01
 */
public class StatTerminal implements Serializable {

    private int id;//主键

    private Date statDate;//统计时间

    private int statCount;//统计数量

    private int type;//类型，0：一般在线统计，1：按月统计

    public StatTerminal() {
    }

    public StatTerminal(Date statDate, int statCount, int type) {
        this.statDate = statDate;
        this.statCount = statCount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public int getStatCount() {
        return statCount;
    }

    public void setStatCount(int statCount) {
        this.statCount = statCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
