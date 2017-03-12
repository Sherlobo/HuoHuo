package com.Huohuo.Huohuo;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-03-10.
 */

public class Client implements Serializable{

    private int headShotId;
    private String name;
    private int orderCount;

    public Client(int headShotId, String name, int orderCount) {
        this.headShotId = headShotId;
        this.name = name;
        this.orderCount = orderCount;
    }

    public int getHeadShotId() {
        return headShotId;
    }

    public void setHeadShotId(int headShotId) {
        this.headShotId = headShotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
