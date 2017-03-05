package com.Huohuo.Huohuo.order.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Order_finished;
import com.Huohuo.Huohuo.Order_finishedAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentOrderFinishedBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderFinishedFragment extends BaseFragment<FragmentOrderFinishedBinding> {
    private List<Order_finished> order_finishedList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_order_finished;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initOrderFinished();
        initRecycleView();

    }

    private void initRecycleView() {
        RecyclerView recyclerView=bindingView.recycleView;
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Order_finishedAdapter adapter=new Order_finishedAdapter(order_finishedList);
        recyclerView.setAdapter(adapter);
    }
    private void initOrderFinished() {
        for(int i=0;i<2;i++){
            Order_finished order1=new Order_finished("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
            order_finishedList.add(order1);
            Order_finished order2=new Order_finished("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
            order_finishedList.add(order2);
            Order_finished order3=new Order_finished("2017年3月14日 21:00","南京夫子庙","上海外滩","预计费用","其他费用","3000元","发送中");
            order_finishedList.add(order3);

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
