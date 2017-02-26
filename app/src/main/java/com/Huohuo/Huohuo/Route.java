package com.Huohuo.Huohuo;

/**
 * Created by yqhok on 2017-02-26.
 */

public class Route {

    private String starting;
    private String shipper;
    private String destination;
    private String receiver;

    public Route(String starting, String shipper, String destination, String receiver) {
        this.starting = starting;
        this.shipper = shipper;
        this.destination = destination;
        this.receiver = receiver;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getStarting() {
        return starting;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getShipper() {
        return shipper;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }
}
