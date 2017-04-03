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
    private String truckId;
    private String remark;
    private Driver driver;
    private String driverId;
    private double mile;
    private double price;
    private int status;
    private String evaluateToDriver;
    private String evaluateToClient;
    private boolean isPayed = false;

    public static final int PENDING = 0;
    public static final int UNDERWAY = 1;
    public static final int WAITING = 2;
    public static final int UNCONFIRMED = 3;
    public static final int FINISHED = 4;

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
        if (status == PENDING || status == UNDERWAY || status == WAITING || status == UNCONFIRMED || status == FINISHED) {
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

    public String getEvaluateToDriver() {
        return evaluateToDriver;
    }

    public void setEvaluateToDriver(String evaluateToDriver) {
        this.evaluateToDriver = evaluateToDriver;
    }

    public String getEvaluateToClient() {
        return evaluateToClient;
    }

    public void setEvaluateToClient(String evaluateToClient) {
        this.evaluateToClient = evaluateToClient;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

}
