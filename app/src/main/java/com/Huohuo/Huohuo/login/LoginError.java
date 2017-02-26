package com.Huohuo.Huohuo.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yqhok.project_2.R;

public class LoginError extends AppCompatActivity implements View.OnClickListener {

    private Button mCross;
    private Button mUseIdentify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_error);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
        mCross =(Button) findViewById(R.id.Cross);
        mCross.setOnClickListener(this);
        mUseIdentify=(Button) findViewById(R.id.UseIdentify);
        mUseIdentify.setOnClickListener(this);
        String str="你可以通过手机号+短信验证码进行修改密码";
        int fstart=str.indexOf("手机号+短信验证码");
        int fend=fstart+"手机号+短信验证码".length();
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.GREEN),fstart,fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView mtext=(TextView) findViewById(R.id.text);
        mtext.setText(style);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Cross:
                finish();
                break;
            case R.id.UseIdentify:

                break;
            default:
                break;
        }

    }
}
