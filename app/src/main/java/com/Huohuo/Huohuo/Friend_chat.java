package com.Huohuo.Huohuo;

import com.Huohuo.Huohuo.base.BaseActivity;

/**
 * Created by Tony on 2017/3/6.
 */

public class Friend_chat extends BaseActivity {
    private String name;
    private String chat;
    private String time;
    public Friend_chat(String name, String chat, String time){
        this.name=name;
        this.chat=chat;
        this.time=time;
    }
    public String getName(){
        return name;
    }
    public String getTime(){
        return time;
    }
    public String getChat(){
        return chat;
    }
}
