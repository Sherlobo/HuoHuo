package com.yqhok.project_2.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yqhok.project_2.MainActivity;
import com.yqhok.project_2.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件对象
    private EditText et_name, et_pass;
    private Button mLoginButton;
    private Button mLoginError;
    private Button mMore;
    private Button mIdentifying;
    private Button mGetIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }

        et_name = (EditText) findViewById(R.id.username);
        et_pass = (EditText) findViewById(R.id.password);

        mLoginButton  = (Button) findViewById(R.id.login);
        mLoginError   = (Button) findViewById(R.id.login_error);
        mIdentifying  = (Button) findViewById(R.id.Identifying);
        mMore          = (Button) findViewById(R.id.more);
        mGetIdentify  = (Button) findViewById(R.id.GetIdentify) ;
        mLoginButton.setOnClickListener(this);
         mLoginError.setOnClickListener(this);
                mMore.setOnClickListener(this);
        mIdentifying.setOnClickListener(this);
        mGetIdentify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:         //登录
                Intent tohomepage = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(tohomepage);
                break;
            case R.id.login_error: //登录遇到问题
                Intent tologinerror = new Intent(LoginActivity.this,LoginError.class);
                startActivity(tologinerror);
                break;
            case R.id.more:         //更多 点击按钮后，加载弹出式菜单
                openDialog();        //利用AlertDialog.Builder类来创建带列表的对话框的方法
                break;
            case R.id.Identifying: //用验证码登录
                if(mGetIdentify.getVisibility()==View.INVISIBLE) {
                    mGetIdentify.setVisibility(View.VISIBLE);
                    mIdentifying.setText("使用密码登录");
                }
                else if(mGetIdentify.getVisibility()==View.VISIBLE){
                    mGetIdentify.setVisibility(View.INVISIBLE);
                    mIdentifying.setText("使用验证码登录");
                }
                break;
            case R.id.GetIdentify: //获取验证码

                break;
            default:
                break;
        }
    }

    /*利用AlertDialog.Builder类来创建带列表的对话框*/
    protected void openDialog(){
        final String[] items={"帮助中心","注册","关于我们"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //实例化AlertDialog.Builder类
        //添加列表项
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Toast.makeText(LoginActivity.this, items[which], Toast.LENGTH_SHORT).show();//弹出消息提示框
                        break;
                    case 1:
                        Intent intent1 = new Intent(LoginActivity.this,Register.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Toast.makeText(LoginActivity.this, items[which], Toast.LENGTH_SHORT).show();//弹出消息提示框
                        break;
                }
            }
        });
        builder.create().show();//创建并显示对话框
    }


}
