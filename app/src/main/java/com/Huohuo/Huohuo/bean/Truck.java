package com.Huohuo.Huohuo.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by yqhok on 2017-03-09.
 */

public class Truck extends DataSupport implements Serializable {
    private int truck_picture;
    private String kind;
    private String weight;
    private String inicost;
    private String size;
    private String overcost;
    public Truck(String kind,String weight,String inicost,String size,String overcost,int truck_picture){
        this.kind=kind;
        this.weight=weight;
        this.inicost=inicost;
        this.size=size;
        this.overcost=overcost;
        this.truck_picture=truck_picture;
    }
    public void setKind(String kind) {
        this.kind=kind;
    }

    public String getKind() {
        return kind;
    }

    public void setInicost(String inicost) {
        this.inicost = inicost;
    }

    public String getInicost() {
        return inicost;
    }

    public void setWeight(String weight) {
        this.weight=weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setSize(String size) {
        this.size=size;
    }

    public String getSize() {
        return size;
    }

    public void setOvercost(String overcost) {
        this.overcost=overcost;
    }

    public String getOvercost() {
        return overcost;
    }


}
