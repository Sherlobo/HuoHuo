package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.Huohuo.Huohuo.adapter.DriverAdapter;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Driver;
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
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DriverAdapter adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DriverAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Driver driver) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                if (intent.getStringExtra("handle") == "set") {
                    intent = new Intent();
                    bundle.putSerializable("Driver", driver);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    intent = new Intent(HomeCommonUsedDriverActivity.this, DriverInfoActivity.class);
                    bundle.putSerializable("Driver", driver);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
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
