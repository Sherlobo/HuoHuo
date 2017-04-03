package com.Huohuo.Huohuo.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.Huohuo.Huohuo.bean.Truck;
import com.Huohuo.Huohuo.databinding.ActivityLoginBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;


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
        LitePal.getDatabase();
        initTruck();
        initView();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
    }

    private void initTruck() {
        DataSupport.deleteAll(Truck.class);
        Truck truck1 = new Truck();
        truck1.setObjectId("58d904caa22b9d00646e595f");
        truck1.setImageId(R.drawable.truck_order_1);
        truck1.setType("小型货车");
        truck1.setSize("2.8*1.6*1.5m");
        truck1.setInicost(88);
        truck1.setOvercost(6);
        truck1.setWeight("1.5吨");
        truck1.save();
        Truck truck2 = new Truck();
        truck2.setObjectId("58d904f30ce463005710e821");
        truck2.setImageId(R.drawable.truck_order_2);
        truck2.setType("中型货车");
        truck2.setSize("4.2*1.8*1.8m");
        truck2.setInicost(158);
        truck2.setOvercost(7);
        truck2.setWeight("1.8吨");
        truck2.save();
        Truck truck3 = new Truck();
        truck3.setObjectId("58d904ad61ff4b006cd745d2");
        truck3.setImageId(R.drawable.truck_order_3);
        truck3.setType("依维柯");
        truck3.setSize("2.5*1.9*1.3m");
        truck3.setInicost(88);
        truck3.setOvercost(5);
        truck3.setWeight("1.6吨");
        truck3.save();
        Truck truck4 = new Truck();
        truck4.setObjectId("58d9047544d90400687d691d");
        truck4.setImageId(R.drawable.truck_order_4);
        truck4.setType("金杯车");
        truck4.setSize("2.7*1.3*1.1m");
        truck4.setInicost(68);
        truck4.setOvercost(5);
        truck4.setWeight("1.5吨");
        truck4.save();
        Truck truck5 = new Truck();
        truck5.setObjectId("58d9038761ff4b006cd73c50");
        truck5.setImageId(R.drawable.truck_order_5);
        truck5.setType("面包车");
        truck5.setSize("1.7*1.2*1m");
        truck5.setInicost(40);
        truck5.setOvercost(3.5);
        truck5.setWeight("3公斤内");
        truck5.save();
        Truck truck6 = new Truck();
        truck6.setObjectId("58d903940ce463005710def1");
        truck6.setImageId(R.drawable.truck_order_6);
        truck6.setType("小件送");
        truck6.setSize("0.3*0.3*0.2m");
        truck6.setInicost(12);
        truck6.setOvercost(2);
        truck6.setWeight("1.5吨");
        truck6.save();
    }

    private void initView() {
        phone = bindingView.phone;
        password = bindingView.password;
        login = bindingView.login;
        loginError = bindingView.loginError;
        more = bindingView.more;
        getIdentify = bindingView.GetIdentify;
        getIdentify.setVisibility(View.INVISIBLE);
        identifying = bindingView.Identifying;
        identifying.setText("使用验证码登录");
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
                final String strPhone = phone.getText().toString();
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
                                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                                editor.putString("phone", avUser.getMobilePhoneNumber());
                                editor.apply();
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
