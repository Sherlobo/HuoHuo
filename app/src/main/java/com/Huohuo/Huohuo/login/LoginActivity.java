package com.Huohuo.Huohuo.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Huohuo.Huohuo.MainActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityLoginBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import org.json.JSONObject;
import org.litepal.LitePal;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    private EditText phone;
    private EditText password;
    private Button login;
    private Button loginError;
    private Button more;
    private Button identifying;
    private Button getIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showContentView();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        LitePal.getDatabase();
        phone = bindingView.phone;
        password = bindingView.password;
        login = bindingView.login;
        loginError = bindingView.loginError;
        more = bindingView.more;
        getIdentify = bindingView.GetIdentify;
        identifying = bindingView.Identifying;
        login.setOnClickListener(this);
        loginError.setOnClickListener(this);
        more.setOnClickListener(this);
        identifying.setOnClickListener(this);
        getIdentify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String strPhone = phone.getText().toString();
                String strPassword = password.getText().toString();
                if (strPhone == null || strPhone.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (strPassword == null || strPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    AVUser.logInInBackground(strPhone, strPassword, new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null) {
                                LoginActivity.this.finish();
                                MainActivity.start(LoginActivity.this);
                            } else {
                                parseJSONWithJSONObject(e.getMessage());
                            }
                        }
                    });
                }
                break;
            case R.id.login_error:
                Intent intentToLoginError = new Intent(LoginActivity.this,LoginError.class);
                startActivity(intentToLoginError);
                break;
            case R.id.more:
                openDialog();
                break;
            case R.id.Identifying:
                if(getIdentify.getVisibility() == View.INVISIBLE) {
                    getIdentify.setVisibility(View.VISIBLE);
                    identifying.setText("使用密码登录");
                }
                else if(getIdentify.getVisibility() == View.VISIBLE) {
                    getIdentify.setVisibility(View.INVISIBLE);
                    identifying.setText("使用验证码登录");
                }
                break;
            case R.id.GetIdentify:
                break;
            default:
                break;
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String code = jsonObject.getString("code");
            switch (code) {
                case "211":
                    Toast.makeText(LoginActivity.this, "该用户还未注册", Toast.LENGTH_SHORT).show();
                    break;
                case "210":
                    Toast.makeText(LoginActivity.this, "用户名或密码输入错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(LoginActivity.this, jsonData, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void openDialog(){
        final String[] items = {"帮助中心","注册","关于我们"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Toast.makeText(LoginActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Toast.makeText(LoginActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.create().show();
    }

}
