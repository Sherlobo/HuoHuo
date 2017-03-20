package com.Huohuo.Huohuo.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.MyFragmentPagerAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentHomeBinding;
import com.Huohuo.Huohuo.home.child.HomeCommonUsedFragment;
import com.Huohuo.Huohuo.home.child.HomeInsuranceFragment;
import com.Huohuo.Huohuo.home.child.HomeMessageFragment;

import java.util.ArrayList;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private ArrayList<String> titleList = new ArrayList<>(3);
    private ArrayList<Fragment> fragmentsList = new ArrayList<>(3);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initFragment();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList, titleList);
        bindingView.vpHome.setAdapter(myAdapter);
        bindingView.vpHome.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabHome.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabHome.setupWithViewPager(bindingView.vpHome);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    private void initFragment() {
        titleList.add("信息");
        titleList.add("常用");
        titleList.add("保险");
        fragmentsList.add(new HomeMessageFragment());
        fragmentsList.add(new HomeCommonUsedFragment());
        fragmentsList.add(new HomeInsuranceFragment());
    }
}
