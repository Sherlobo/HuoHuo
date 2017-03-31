package com.Huohuo.Huohuo.home.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.HomeCommonUsedDriverActivity;
import com.Huohuo.Huohuo.HomeCommonUsedRouteActivity;
import com.Huohuo.Huohuo.HomeCommonUsedTruckActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.DriverAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.databinding.FragmentHomeCommonUsedBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeCommonUsedFragment extends BaseFragment<FragmentHomeCommonUsedBinding> implements View.OnClickListener {

    private List<Driver> driverList = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
        initDriver();
        initRecycleView();
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
        recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DriverAdapter adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
    }

    private void initDriver() {
        List<Driver> driverList = DataSupport.findAll(Driver.class);
        AVQuery<AVObject> query = new AVQuery<>("Driver");
        for (final Driver driver : driverList) {
            query.getInBackground(driver.getObjectId(), new GetCallback<AVObject>() {
                @Override
                public void done(AVObject avObject, AVException e) {
                    if (e == null) {
                        driver.setTaskCount(Integer.parseInt(avObject.get("taskCount").toString()));
                        driver.setRating(Integer.parseInt(avObject.get("rating").toString()));
                        driver.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                        driver.save();
                    }
                }
            });
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
    public void onDestroy() {
        super.onDestroy();
    }
}
