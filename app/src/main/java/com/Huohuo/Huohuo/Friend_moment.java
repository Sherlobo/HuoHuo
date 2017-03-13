package com.Huohuo.Huohuo;

import com.Huohuo.Huohuo.base.BaseActivity;

/**
 * Created by Tony on 2017/3/5.
 */

public class Friend_moment extends BaseActivity {
    private String name;
    private String time;
    private String evaluate;
    private Float rating;

    public Friend_moment(String name,String time,String evaluate, Float rating){
        this.name=name;
        this.time=time;
        this.evaluate=evaluate;
        this.rating = rating;
    }

    public String getName(){
        return name;
    }
    public String getTime(){
        return time;
    }
    public String getEvaluate(){
        return evaluate;
    }
    public void setRating(float rating) {
        if (rating > 0 && rating < 5) {
            this.rating = rating;
        }
    }

    public float getRating() {
        return rating;
    }
}
