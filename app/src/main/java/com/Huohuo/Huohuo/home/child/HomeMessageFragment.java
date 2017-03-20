package com.Huohuo.Huohuo.home.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.Huohuo.Huohuo.OrderInfoActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.OrderAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.FragmentHomeMessageBinding;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeMessageFragment extends BaseFragment<FragmentHomeMessageBinding> {

    private List<OrderForm> orderFormList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private OrderAdapter adapter;
    private Banner banner;

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
        return R.layout.fragment_home_message;
    }

    private void initView() {
        banner = bindingView.banner;
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load("").into(imageView);
            }
        });
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

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(orderFormList);
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
        recyclerView.setAdapter(adapter);
    }

    private void initOrder(){
        List<OrderForm> list = DataSupport.findAll(OrderForm.class);
        orderFormList = new ArrayList<>();
        for (OrderForm orderForm : list) {
            if (orderForm.getStatus() == OrderForm.PENDING) {
                orderFormList.add(orderForm);
            }
        }
        for (OrderForm orderForm : list) {
            if (orderForm.getStatus() == OrderForm.UNDERWAY) {
                orderFormList.add(orderForm);
            }
        }
        for (OrderForm orderForm : list) {
            if (orderForm.getStatus() == OrderForm.WAITING) {
                orderFormList.add(orderForm);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.recycleView.setFocusable(false);
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