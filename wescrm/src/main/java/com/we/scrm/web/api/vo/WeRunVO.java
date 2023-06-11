package com.we.scrm.web.api.vo;

public class WeRunVO extends  AbstractVO{
    private String toOpenid;
    private Integer steps;

    public String getToOpenid() {
        return toOpenid;
    }

    public void setToOpenid(String toOpenid) {
        this.toOpenid = toOpenid;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }
}
