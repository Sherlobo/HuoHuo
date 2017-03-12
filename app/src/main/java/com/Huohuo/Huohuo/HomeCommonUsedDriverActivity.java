package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedDriverBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedDriverActivity extends BaseActivity<ActivityHomeCommonUsedDriverBinding> implements View.OnClickListener {

    private List<Driver> driverList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_common_used_driver);
        showContentView();
        setTitle("常用司机");
        initDrivers();
        initRecycleView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedDriverActivity.class);
        context.startActivity(intent);
    }

    private void initDrivers() {
        for (int i = 0; i < 2; i ++) {
            Driver driverHuang = new Driver(R.mipmap.ic_launcher, "黄师傅", "浙江 杭州", 4.5f, 100);
            driverList.add(driverHuang);
            Driver driverZhang = new Driver(R.mipmap.ic_launcher, "张师傅", "浙江 杭州", 5.0f, 300);
            driverList.add(driverZhang);
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DriverAdapter adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DriverAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Driver d) {
                Driver driver = d;
                String strDriver = driver.getName();
                Intent intent = new Intent();
                intent.putExtra("data_return", strDriver);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home_common_used, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                AddNewDriverActivity.start(HomeCommonUsedDriverActivity.this);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_card:
                PersonalInfoActivity.start(HomeCommonUsedDriverActivity.this);
                break;
        }
    }
}
