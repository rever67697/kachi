package com.team.vo.stat;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:16 2018/8/16
 */
public class StatBean implements Serializable {

    private Integer mTCount;//今月终端激活数量

    private Integer dTCount;//今日终端激活数量

    private Integer tTCount;//终端总数

    private Double dUserFlow;//今日用户消耗流量

    private Double dCardFlow;//今日流量卡消耗

    private Double mUserFlow;//今月用户消耗

    private Double mCardFlow;//今月流量卡消耗

    private Double tChargeFlow;//今月终端充值流量

    private Double tResidueFlow;//终端剩余流量

    private Long tDuration;//今月终端使用时长

    private Double mMaxFlow;//今月流量卡套餐总流量

    private Double mUsedFlow;//今月流量卡套餐使用流量

    private Double mResidueFlow;//今月流量卡套餐剩余流量

    private Double dChargeFlow;//今天充值流量

    private Integer dChargeDate;//今天充值天数

    private String tAvgCost;//终端平均消耗

    private String cCostRate;//卡套餐流量消耗

    private int cChargeFlow;//今天充值流量用户人数

    private int cChargeDate;//今天充值天数用户人数

    private int cChargeTotal;//今天充值总人数

    private String tChargeFlowStr;//今月终端充值流量-显示

    private String tResidueFlowStr;//终端剩余流量-显示

    private String mUserFlowStr;//当月用户消耗-显示

    private String mCardFlowStr;//当月流量卡消耗-显示

    private String mMaxFlowStr;//今月流量卡套餐总流量-显示

    private String mUsedFlowStr;//今月流量卡套餐使用流量-显示

    private String mResidueFlowStr;//今月流量卡套餐剩余流量-显示


    private static DecimalFormat df = new DecimalFormat("0.00");


    public Integer getmTCount() {
        return mTCount;
    }

    public void setmTCount(Integer mTCount) {
        this.mTCount = mTCount;
    }

    public Integer getdTCount() {
        return dTCount;
    }

    public void setdTCount(Integer dTCount) {
        this.dTCount = dTCount;
    }

    public Integer gettTCount() {
        return tTCount;
    }

    public void settTCount(Integer tTCount) {
        this.tTCount = tTCount;
    }

    public Double getdUserFlow() {
        return dUserFlow;
    }

    public void setdUserFlow(Double dUserFlow) {
        this.dUserFlow = dUserFlow;
    }

    public Double getdCardFlow() {
        return dCardFlow;
    }

    public void setdCardFlow(Double dCardFlow) {
        this.dCardFlow = dCardFlow;
    }

    public Double getmUserFlow() {
        return mUserFlow;
    }

    public void setmUserFlow(Double mUserFlow) {
        this.mUserFlow = mUserFlow;
    }

    public Double getmCardFlow() {
        return mCardFlow;
    }

    public void setmCardFlow(Double mCardFlow) {
        this.mCardFlow = mCardFlow;
    }

    public Double gettChargeFlow() {
        return tChargeFlow;
    }

    public void settChargeFlow(Double tChargeFlow) {
        this.tChargeFlow = tChargeFlow;
    }

    public Double gettResidueFlow() {
        return tResidueFlow;
    }

    public void settResidueFlow(Double tResidueFlow) {
        this.tResidueFlow = tResidueFlow;
    }

    public Long gettDuration() {
        return tDuration;
    }

    public void settDuration(Long tDuration) {
        this.tDuration = tDuration;
    }

    public Double getmMaxFlow() {
        return mMaxFlow;
    }

    public void setmMaxFlow(Double mMaxFlow) {
        this.mMaxFlow = mMaxFlow;
    }

    public Double getmUsedFlow() {
        return mUsedFlow;
    }

    public void setmUsedFlow(Double mUsedFlow) {
        this.mUsedFlow = mUsedFlow;
    }

    public Double getmResidueFlow() {
        return mResidueFlow;
    }

    public void setmResidueFlow(Double mResidueFlow) {
        this.mResidueFlow = mResidueFlow;
    }

    public Double getdChargeFlow() {
        return dChargeFlow;
    }

    public void setdChargeFlow(Double dChargeFlow) {
        this.dChargeFlow = dChargeFlow;
    }

    public Integer getdChargeDate() {
        return dChargeDate;
    }

    public void setdChargeDate(Integer dChargeDate) {
        this.dChargeDate = dChargeDate;
    }

    public String gettAvgCost() {
        return tAvgCost;
    }

    public void settAvgCost(String tAvgCost) {
        this.tAvgCost = tAvgCost;
    }

    public String getcCostRate() {
        return cCostRate;
    }

    public void setcCostRate(String cCostRate) {
        this.cCostRate = cCostRate;
    }

    public String gettChargeFlowStr() {
        return tChargeFlowStr;
    }

    public void settChargeFlowStr(String tChargeFlowStr) {
        this.tChargeFlowStr = tChargeFlowStr;
    }

    public String gettResidueFlowStr() {
        return tResidueFlowStr;
    }

    public void settResidueFlowStr(String tResidueFlowStr) {
        this.tResidueFlowStr = tResidueFlowStr;
    }

    public String getmUserFlowStr() {
        return mUserFlowStr;
    }

    public void setmUserFlowStr(String mUserFlowStr) {
        this.mUserFlowStr = mUserFlowStr;
    }

    public String getmCardFlowStr() {
        return mCardFlowStr;
    }

    public void setmCardFlowStr(String mCardFlowStr) {
        this.mCardFlowStr = mCardFlowStr;
    }

    public String getmMaxFlowStr() {
        return mMaxFlowStr;
    }

    public void setmMaxFlowStr(String mMaxFlowStr) {
        this.mMaxFlowStr = mMaxFlowStr;
    }

    public String getmUsedFlowStr() {
        return mUsedFlowStr;
    }

    public void setmUsedFlowStr(String mUsedFlowStr) {
        this.mUsedFlowStr = mUsedFlowStr;
    }

    public String getmResidueFlowStr() {
        return mResidueFlowStr;
    }

    public void setmResidueFlowStr(String mResidueFlowStr) {
        this.mResidueFlowStr = mResidueFlowStr;
    }

    public int getcChargeFlow() {
        return cChargeFlow;
    }

    public void setcChargeFlow(int cChargeFlow) {
        this.cChargeFlow = cChargeFlow;
    }

    public int getcChargeDate() {
        return cChargeDate;
    }

    public void setcChargeDate(int cChargeDate) {
        this.cChargeDate = cChargeDate;
    }

    public int getcChargeTotal() {
        return cChargeTotal;
    }

    public void setcChargeTotal(int cChargeTotal) {
        this.cChargeTotal = cChargeTotal;
    }


    public void fix() {
        this.mResidueFlow = this.mMaxFlow - this.mUsedFlow;
        this.tAvgCost = df.format(tTCount > 0 ? mUserFlow / tTCount : 0);
        this.cCostRate = df.format(mMaxFlow > 0 ? (mUsedFlow / mMaxFlow * 100) : 0);

        this.tChargeFlowStr = this.tChargeFlow > 1024 ? df.format(this.tChargeFlow / 1024) + "T" : this.tChargeFlow + "G";
        this.tResidueFlowStr = this.tResidueFlow > 1024 ? df.format(this.tResidueFlow / 1024) + "T" : this.tResidueFlow + "G";
        this.mUserFlowStr = this.mUserFlow > 1024 ? df.format(this.mUserFlow / 1024) + "T" : this.mUserFlow + "G";
        this.mCardFlowStr = this.mCardFlow > 1024 ? df.format(this.mCardFlow / 1024) + "T" : this.mCardFlow + "G";
        this.mMaxFlowStr = this.mMaxFlow > 1024 ? df.format(this.mMaxFlow / 1024) + "T" : this.mMaxFlow + "G";
        this.mUsedFlowStr = this.mUsedFlow > 1024 ? df.format(this.mUsedFlow / 1024) + "T" : this.mUsedFlow + "G";
        this.mResidueFlowStr = this.mResidueFlow > 1024 ? df.format(this.mResidueFlow / 1024) + "T" : this.mResidueFlow + "G";

    }
}

