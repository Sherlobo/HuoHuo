package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.databinding.ActivityDriverInfoBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class DriverInfoActivity extends BaseActivity<ActivityDriverInfoBinding> implements View.OnClickListener {

    private CircleImageView headShot;
    private TextView realName;
    private TextView briefIntroduce;
    private TextView taskCount;

    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        showContentView();
        initDriver();
        initView();
    }

    private void initDriver() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        driver = (Driver) bundle.getSerializable("driver");
    }

    private void initView() {
        headShot = bindingView.headShot;
        realName = bindingView.realName;
        briefIntroduce = bindingView.briefIntro;
        taskCount = bindingView.taskCount;
        realName.setText(driver.getRealName());
        taskCount.setText("" + driver.getTaskCount());
        briefIntroduce.setText(driver.getBriefIntroduce());
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DriverInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
