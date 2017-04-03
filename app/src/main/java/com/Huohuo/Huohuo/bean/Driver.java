package com.Huohuo.Huohuo.bean;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-02-26.
 */

public class Driver extends Person implements Serializable {

    private Float rating;
    private int taskCount;

    public void setRating(float rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public float getRating() {
        return rating;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getTaskCount() {
        return taskCount;
    }

}
