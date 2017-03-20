package com.Huohuo.Huohuo.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Tony on 2017/3/4.
 */

public class OrderForm extends DataSupport implements Serializable {

    private String objectId;
    private String startTime;
    private String shipper;
    private String starting;
    private String receiver;
    private String destination;
    private String weight;
    private String typeOfGoods;
    private Truck truck;
    private String remark;
    private Driver driver;
    private double mile;
    private double price;
    private int status;

    public static final int PENDING = 0;
    public static final int UNDERWAY = 1;
    public static final int WAITING = 2;
    public static final int FINISHED = 3;

    public String  getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public double getMile() {
        return mile;
    }

    public void setMile(double mile) {
        this.mile = mile;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == PENDING || status == UNDERWAY || status == WAITING || status == FINISHED) {
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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
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
