package com.Huohuo.Huohuo;

/**
 * Created by Tony on 2017/3/2.
 */

public class Order_waiting {
        private String time;
        private String starting;
        private String destination;
        private String cost;
        private String othercost;
        private String allcost;
        private String status;

        public Order_waiting(String time,String starting,String destination,String cost,String othercost,String allcost,String status){
            this.time=time;
            this.starting=starting;
            this.destination=destination;
            this.cost=cost;
            this.othercost=othercost;
            this.allcost=allcost;
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
        public  String getOthercost(){
        return othercost;
    }
        public String getAllcost(){return allcost;}
        public String getStatus(){
            return status;
        }

}
