package com.Huohuo.Huohuo.home.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.DriverInfoActivity;
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
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeCommonUsedFragment extends BaseFragment<FragmentHomeCommonUsedBinding> implements View.OnClickListener {

    private List<Driver> driverList = new ArrayList<>();

    private RecyclerView recyclerView;
    private DriverAdapter adapter;

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
        adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DriverAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Driver driver) {
                Intent intent = new Intent(getActivity(), DriverInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("driver", driver);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initDriver() {
        AVQuery<AVObject> query = new AVQuery<>("Driver");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    driverList.clear();
                    for (AVObject avObject : list) {
                        Driver driver = new Driver();
                        driver.setObjectId(avObject.getObjectId());
                        driver.setPhone(avObject.get("phone").toString());
                        driver.setRealName(avObject.get("realName").toString());
                        driver.setIdNumber(avObject.get("idNumber").toString());
                        driver.setTaskCount(Integer.parseInt(avObject.get("taskCount").toString()));
                        driver.setRating(Float.parseFloat(avObject.get("rating").toString()));
                        driver.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                        driverList.add(driver);
                    }
                    initRecycleView();
                }
            }
        });
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
