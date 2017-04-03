package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.adapter.DriverAdapter;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedDriverBinding;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedDriverActivity extends BaseActivity<ActivityHomeCommonUsedDriverBinding> {

    private List<Driver> driverList = new ArrayList<>();

    private RecyclerView recyclerView;
    private DriverAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_common_used_driver);
        showContentView();
        setTitle("常用司机");
        initDriver();
        initRecycleView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedDriverActivity.class);
        context.startActivity(intent);
    }

    private void initDriver() {
        driverList.clear();
        List<Driver> list = DataSupport.findAll(Driver.class);
        for (Driver driver : list) {
            driverList.add(driver);
        }
    }

    private void initRecycleView() {
        recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DriverAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Driver driver) {
                Intent intent = new Intent(HomeCommonUsedDriverActivity.this, DriverInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("driver", driver);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
