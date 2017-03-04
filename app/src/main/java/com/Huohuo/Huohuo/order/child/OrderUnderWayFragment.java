package com.Huohuo.Huohuo.order.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Order_underway;
import com.Huohuo.Huohuo.Order_underwayAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentOrderUnderwayBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderUnderWayFragment extends BaseFragment<FragmentOrderUnderwayBinding> {
    private List<Order_underway> order_underwayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initOrderUnderway();
        initRecycleView();
        loadData();
    }
    @Override
    public int setContent() {
        return R.layout.fragment_order_underway;
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Order_underwayAdapter adapter = new Order_underwayAdapter(order_underwayList);
        recyclerView.setAdapter(adapter);
    }
    private void initOrderUnderway(){
        for(int i=0;i<2;i++){
            Order_underway order1=new Order_underway("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","发送中");
            order_underwayList.add(order1);
            Order_underway order2=new Order_underway("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","发送中");
            order_underwayList.add(order2);
            Order_underway order3=new Order_underway("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","发送中");
            order_underwayList.add(order3);
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
