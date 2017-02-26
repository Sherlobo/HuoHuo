package com.Huohuo.Huohuo.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.Huohuo.Huohuo.base.BaseFragment;
import com.yqhok.project_2.R;
import com.yqhok.project_2.databinding.FragmentHomeMessageBinding;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeMessageFragment extends BaseFragment<FragmentHomeMessageBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
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