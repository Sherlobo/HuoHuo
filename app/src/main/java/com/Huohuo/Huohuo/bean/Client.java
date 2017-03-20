package com.Huohuo.Huohuo.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-03-14.
 */

public class Client extends DataSupport implements Serializable{

    private int headShotId;
    private String realName = "";
    private String briefIntroduce = "";
    private int orderCount;
    private String phone = "";

    public int getHeadShotId() {
        return headShotId;
    }

    public void setHeadShotId(int headShotId) {
        this.headShotId = headShotId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBriefIntroduce() {
        return briefIntroduce;
    }

    public void setBriefIntroduce(String briefIntroduce) {
        this.briefIntroduce = briefIntroduce;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
