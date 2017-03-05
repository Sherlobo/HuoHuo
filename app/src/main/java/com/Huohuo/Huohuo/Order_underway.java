package com.Huohuo.Huohuo;


/**
 * Created by Tony on 2017/3/1.
 */

public class Order_underway {
    private String time;
    private String starting;
    private String destination;
    private String cost;
    private String status;

    public Order_underway(String time,String starting,String destination,String cost,String status){
        this.time=time;
        this.starting=starting;
        this.destination=destination;
        this.cost=cost;
        this.status=status;
    }
    public String getTime(){
        return time;
    }
    public String getStarting(){
        return starting;
    }
    public String getDestination(){
        return destination;
    }
    public  String getCost(){
        return cost;
    }
    public String getStatus(){
        return status;
    }
}
