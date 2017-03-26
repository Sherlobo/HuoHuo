package com.Huohuo.Huohuo.bean;

/**
 * Created by JinBo on 2017/3/19.
 */

public class Insurance {
    private String kind;
    private String cost;
    public Insurance(String kind,String cost){
        this.kind=kind;
        this.cost=cost;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }
}
