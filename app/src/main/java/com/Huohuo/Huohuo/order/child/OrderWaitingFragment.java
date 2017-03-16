package com.Huohuo.Huohuo.order.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Order;
import com.Huohuo.Huohuo.OrderAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentOrderWaitingBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderWaitingFragment extends BaseFragment<FragmentOrderWaitingBinding> {
    private List<Order>  orderList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public int setContent() {
        return R.layout.fragment_order_waiting;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initOrder();
        initRecycleView();

    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);
    }

    private void initOrder(){
        Order order1=new Order("2017年3月16日","啥玩意","南京","杭州电子科技大学","杭州","1000kg","服装","请快速送达",0);
        orderList.add(order1);
        Order order2=new Order("2017年3月16日","啥玩意","南京","杭州电子科技大学","杭州","1000kg","服装","请快速送达",0);
        orderList.add(order2);
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


