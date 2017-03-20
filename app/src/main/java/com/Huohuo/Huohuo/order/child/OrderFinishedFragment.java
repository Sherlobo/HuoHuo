package com.Huohuo.Huohuo.order.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.OrderInfoActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.OrderAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.FragmentOrderFinishedBinding;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderFinishedFragment extends BaseFragment<FragmentOrderFinishedBinding> {

    private List<OrderForm> orderFormList;

    private SwipeRefreshLayout swipeRefreshLayout;
    private OrderAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
        initOrder();
        initRecycleView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_order_finished;
    }

    private void initView() {
        swipeRefreshLayout = bindingView.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.Red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onRefresh();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void initOrder(){
        List<OrderForm> list = DataSupport.findAll(OrderForm.class);
        orderFormList = new ArrayList<>();
        for (OrderForm orderForm : list) {
            if (orderForm.getStatus() == OrderForm.FINISHED) {
                orderFormList.add(orderForm);
            }
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(orderFormList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OrderAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, OrderForm orderForm) {
                Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("orderForm", orderForm);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.recyclerView.setFocusable(false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onRefresh() {
        showContentView();
        initView();
        initOrder();
        initRecycleView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
