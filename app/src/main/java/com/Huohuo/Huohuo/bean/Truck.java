package com.Huohuo.Huohuo.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-03-09.
 */

public class Truck extends DataSupport implements Serializable {

    private String objectId;
    private String type;
    private String weight;
    private double inicost;
    private String size;
    private double overcost;
    private int imageId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setInicost(double inicost) {
        this.inicost = inicost;
    }

    public double getInicost() {
        return inicost;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setOvercost(double overcost) {
        this.overcost = overcost;
    }

    public double getOvercost() {
        return overcost;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
