package com.Huohuo.Huohuo.bean;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-03-14.
 */

public class Client extends Person implements Serializable {

    private int orderCount;
    private int points;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
