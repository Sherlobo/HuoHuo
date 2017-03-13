package com.Huohuo.Huohuo.login;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityLoginErrorBinding;


public class LoginError extends BaseActivity<ActivityLoginErrorBinding> implements View.OnClickListener {

    private Button useIdentify;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_error);
        useIdentify = bindingView.UseIdentify;
        useIdentify.setOnClickListener(this);
        String str="你可以通过手机号+短信验证码进行修改密码";
        int fstart = str.indexOf("手机号+短信验证码");
        int fend = fstart+"手机号+短信验证码".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.GREEN),fstart,fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView = bindingView.text;
        textView.setText(style);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.UseIdentify:
                break;
        }
    }
}
