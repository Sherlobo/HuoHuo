package com.yqhok.project_2.home.child;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.yqhok.project_2.R;
import com.yqhok.project_2.base.BaseFragment;
import com.yqhok.project_2.databinding.FragmentHomeMessageBinding;
import com.yqhok.project_2.databinding.HeaderHomeMessageBinding;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeMessageFragment extends BaseFragment<FragmentHomeMessageBinding> {

    private View mHeaderView;
    private HeaderHomeMessageBinding mHeaderBinding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_home_message, null, false);
        initRecycleView();
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_message;
    }

    private void initRecycleView() {
    }
}