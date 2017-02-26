package com.Huohuo.Huohuo.home.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.Driver;
import com.Huohuo.Huohuo.DriverAdapter;
import com.Huohuo.Huohuo.HomeCommonUsedDriverActivity;
import com.Huohuo.Huohuo.HomeCommonUsedRouteActivity;
import com.Huohuo.Huohuo.HomeCommonUsedTruckActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentHomeCommonUsedBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeCommonUsedFragment extends BaseFragment<FragmentHomeCommonUsedBinding> implements View.OnClickListener {

    private List<Driver> driverList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
        initDrivers();
        initRecycleView();
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_common_used;
    }

    private void initView() {
        bindingView.commonRoute.setOnClickListener(this);
        bindingView.commonDriver.setOnClickListener(this);
        bindingView.commonTruck.setOnClickListener(this);
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DriverAdapter adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
    }

    private void initDrivers() {
        for (int i = 0; i < 2; i ++) {
            Driver driverHuang = new Driver(R.mipmap.ic_launcher, "黄师傅", "浙江 杭州", 4.5f, 100);
            driverList.add(driverHuang);
            Driver driverZhang = new Driver(R.mipmap.ic_launcher, "张师傅", "浙江 杭州", 5.0f, 300);
            driverList.add(driverZhang);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_route:
                HomeCommonUsedRouteActivity.start(getActivity());
                break;
            case R.id.common_driver:
                HomeCommonUsedDriverActivity.start(getActivity());
                break;
            case R.id.common_truck:
                HomeCommonUsedTruckActivity.start(getActivity());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.recycleView.setFocusable(false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onRefresh() {
        showContentView();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
