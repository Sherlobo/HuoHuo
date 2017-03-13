package com.Huohuo.Huohuo.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Huohuo.Huohuo.MainActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityRegisterBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener {

    private EditText phone;
    private EditText identifyingCode;
    private EditText password;
    private EditText confirmPassword;
    private EditText realName;
    private EditText idNumber;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册账号");
        showContentView();
        initView();
    }
    private void initView() {
        phone = bindingView.phone;
        identifyingCode = bindingView.identifyingCode;
        password = bindingView.password;
        confirmPassword = bindingView.confirmPassword;
        realName = bindingView.realName;
        idNumber = bindingView.idNumber;
        register = bindingView.register;
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AVUser user = new AVUser();
        String strPhone = phone.getText().toString();
        String strPassword = password.getText().toString();
        user.setUsername(strPhone);
        user.setMobilePhoneNumber(strPhone);
        user.setPassword(strPassword);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


