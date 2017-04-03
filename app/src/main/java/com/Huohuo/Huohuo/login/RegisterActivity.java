package com.Huohuo.Huohuo.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Huohuo.Huohuo.MainActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityRegisterBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener, TextWatcher {

    private static final String DEFAULT_COUNTRY_ID = "42";

    private EditText phone;
    private EditText identifyingCode;
    private EditText password;
    private EditText confirmPassword;
    private EditText realName;
    private EditText idNumber;
    private Button getIdentifyingCode;
    private Button register;

    private EventHandler eh;
    private EventHandler callback;
    private EventHandler handler;
    private OnSendMessageHandler osmHandler;

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册账号");
        showContentView();
        initView();
        initSMS();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.registerEventHandler(eh);
    }

    private void initView() {
        phone = bindingView.phone;
        phone.addTextChangedListener(this);
        phone.requestFocus();
        identifyingCode = bindingView.identifyingCode;
        identifyingCode.addTextChangedListener(this);
        password = bindingView.password;
        password.addTextChangedListener(this);
        confirmPassword = bindingView.confirmPassword;
        confirmPassword.addTextChangedListener(this);
        realName = bindingView.realName;
        realName.addTextChangedListener(this);
        idNumber = bindingView.idNumber;
        idNumber.addTextChangedListener(this);
        getIdentifyingCode = bindingView.getIdentify;
        getIdentifyingCode.setOnClickListener(this);
        time = new TimeCount(30000, 1000);
        register = bindingView.register;
        register.setOnClickListener(this);
    }

    private void initSMS() {
        if (phone.getText().length() > 0) {
            getIdentifyingCode.setEnabled(true);
        }
        SMSSDK.initSDK(this, "1c9f4e40c35dd", "7647477db833570e894052951db317bf");
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                    }
                } else {
                    ((Throwable)data).printStackTrace();
                }
            }
        };
    }

    public void setRegisterCallback(EventHandler callback) {
        this.callback = callback;
    }

    public void setOnSendMessageHandler(OnSendMessageHandler h) {
        osmHandler = h;
    }

    @Override
    public void onClick(View view) {
        final String strPhone = phone.getText().toString();
        switch (view.getId()) {
            case R.id.register:
                final AVUser user = new AVUser();
                final String strPassword = password.getText().toString();
                final String strRealName = realName.getText().toString();
                final String strIdNumber = idNumber.getText().toString();
                final String strIdentifyingCode = identifyingCode.getText().toString();
                user.setUsername(strPhone);
                user.setMobilePhoneNumber(strPhone);
                user.setPassword(strPassword);
                if (strPhone == null || strPassword == null || strRealName == null || strIdNumber == null || strIdentifyingCode == null) {
                    Toast.makeText(RegisterActivity.this, "身份信息有误，请重试", Toast.LENGTH_SHORT).show();
                } else {
                    AVObject client = new AVObject("Client");
                    client.put("phone", strPhone);
                    client.put("realName", strRealName);
                    client.put("idNumber", strIdNumber);
                    client.put("orderCount", 0);
                    client.put("briefIntroduce", "");
                    client.saveInBackground();
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                                editor.putString("phone", strPhone);
                                editor.apply();
                                SMSSDK.unregisterEventHandler(eh);
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                RegisterActivity.this.finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.getIdentify:
                time.start();
                SMSLog.getInstance().i("verification phone ==>>" + strPhone);
                SMSSDK.getVerificationCode("+" + SMSSDK.getCountry(DEFAULT_COUNTRY_ID)[1], strPhone, osmHandler);
                break;
            default:
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getIdentifyingCode.setBackgroundResource(R.drawable.shape_unclickable);
            getIdentifyingCode.setClickable(false);
            getIdentifyingCode.setText(millisUntilFinished / 1000 + "秒后可重新发送");
        }

        @Override
        public void onFinish() {
            getIdentifyingCode.setBackgroundResource(R.drawable.shape);
            getIdentifyingCode.setClickable(true);
        }
    }

}
