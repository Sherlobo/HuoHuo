package com.Huohuo.Huohuo;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Tony on 2017/3/4.
 */

public class Order extends DataSupport implements Serializable{

    private String time;
    private String shipper;
    private String starting;
    private String receiver;
    private String destination;
    private String weight;
    private String typeOfGoods;
    private String remark;
    private Driver driver;
    private String cost;
    private String othercost;
    private String allcost;
    private int status;

    public static final int UNDERWAY = 0;
    public static final int WAITING = 1;
    public static final int FINISHED = 2;

    public Order(String time, String shipper, String starting, String receiver, String destination, String weight, String typeOfGoods, String remark, int status){
        this.time = time;
        this.shipper = shipper;
        this.starting = starting;
        this.receiver = receiver;
        this.destination = destination;
        this.weight = weight;
        this.typeOfGoods = typeOfGoods;
        this.remark = remark;

        if (status == UNDERWAY || status == WAITING || status == FINISHED) {
            this.status = status;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getOthercost() {
        return othercost;
    }

    public void setOthercost(String othercost) {
        this.othercost = othercost;
    }

    public String getAllcost() {
        return allcost;
    }

    public void setAllcost(String allcost) {
        this.allcost = allcost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == UNDERWAY || status == WAITING || status == FINISHED) {
            this.status = status;
        }
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTypeOfGoods() {
        return typeOfGoods;
    }

    public void setTypeOfGoods(String typeOfGoods) {
        this.typeOfGoods = typeOfGoods;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
