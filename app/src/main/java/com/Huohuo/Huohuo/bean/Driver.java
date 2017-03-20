package com.Huohuo.Huohuo.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-02-26.
 */

public class Driver extends DataSupport implements Serializable {

    private int imageId;
    private String name;
    private String region;
    private Float rating;
    private int taskCount;

    public Driver(int imageId, String name, String region, Float rating, int taskCount) {
        this.imageId = imageId;
        this.name = name;
        this.region = region;
        this.rating = rating;
        this.taskCount = taskCount;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRating(float rating) {
        if (rating > 0 && rating < 5) {
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
