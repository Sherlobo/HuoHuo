package com.Huohuo.Huohuo.order.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Order_waiting;
import com.Huohuo.Huohuo.Order_waitingAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentOrderWaitingBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderWaitingFragment extends BaseFragment<FragmentOrderWaitingBinding> {
    private List<Order_waiting>  order_waitingList=new ArrayList<>();
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
        initOrderWaiting();
        initRecycleView();

    }

    private void initRecycleView() {
        RecyclerView recyclerView=bindingView.recycleView;
       LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Order_waitingAdapter adapter=new Order_waitingAdapter(order_waitingList);
        recyclerView.setAdapter(adapter);
    }

    public void initOrderWaiting(){
            for(int i=0;i<2;i++){
                Order_waiting order1=new Order_waiting("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
                order_waitingList.add(order1);
                Order_waiting order2=new Order_waiting("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
                order_waitingList.add(order2);
                Order_waiting order3=new Order_waiting("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
                order_waitingList.add(order3);

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


