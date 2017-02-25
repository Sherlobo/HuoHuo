package com.yqhok.project_2.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.yqhok.project_2.MyFragmentPagerAdapter;
import com.yqhok.project_2.R;
import com.yqhok.project_2.base.BaseFragment;
import com.yqhok.project_2.order.child.OrderFinishedFragment;
import com.yqhok.project_2.order.child.OrderUnderWayFragment;
import com.yqhok.project_2.order.child.OrderWaitingFragment;
import com.yqhok.project_2.databinding.FragmentOrderBinding;

import java.util.ArrayList;

/**
 * Created by yqhok on 2017-02-22.
 */

public class OrderFragment extends BaseFragment<FragmentOrderBinding> {

    private ArrayList<String> titleList = new ArrayList<>(3);
    private ArrayList<Fragment> fragmentsList = new ArrayList<>(3);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initFragment();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList, titleList);
        bindingView.vpOrder.setAdapter(myAdapter);
        bindingView.vpOrder.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabOrder.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabOrder.setupWithViewPager(bindingView.vpOrder);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_order;
    }

    private void initFragment() {
        titleList.add("进行中");
        titleList.add("待收货");
        titleList.add("已完成");
        fragmentsList.add(new OrderUnderWayFragment());
        fragmentsList.add(new OrderWaitingFragment());
        fragmentsList.add(new OrderFinishedFragment());
    }
}
