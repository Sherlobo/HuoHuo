package com.Huohuo.Huohuo.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.InsuranceAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.Insurance;
import com.Huohuo.Huohuo.databinding.FragmentHomeInsuranceBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeInsuranceFragment extends BaseFragment<FragmentHomeInsuranceBinding> {
    private List<Insurance> insuranceList=new ArrayList<>();
    private InsuranceAdapter adapter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initInsurance();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InsuranceAdapter(insuranceList);
        recyclerView.setAdapter(adapter);
    }

    private void initInsurance() {
        for (int i = 0; i < 2; i ++) {
            Insurance insurance1 = new Insurance("普通货物运险","100元起");
            insuranceList.add(insurance1);
            Insurance insurance2 = new Insurance("普通货物运险","100元起");
            insuranceList.add(insurance2);
        }
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_insurance;
    }
}
