package com.yqhok.project_2.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.yqhok.project_2.MyFragmentPagerAdapter;
import com.yqhok.project_2.R;
import com.yqhok.project_2.base.BaseFragment;
import com.yqhok.project_2.home.child.HomeCommonUsedFragment;
import com.yqhok.project_2.home.child.HomeInsuranceFragment;
import com.yqhok.project_2.home.child.HomeMessageFragment;
import com.yqhok.project_2.databinding.FragmentHomeBinding;

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
