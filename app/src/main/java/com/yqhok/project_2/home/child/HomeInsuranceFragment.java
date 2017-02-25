package com.yqhok.project_2.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yqhok.project_2.R;
import com.yqhok.project_2.base.BaseFragment;
import com.yqhok.project_2.databinding.FragmentHomeInsuranceBinding;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeInsuranceFragment extends BaseFragment<FragmentHomeInsuranceBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_insurance;
    }
}
