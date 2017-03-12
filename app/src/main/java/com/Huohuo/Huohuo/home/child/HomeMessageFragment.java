package com.Huohuo.Huohuo.home.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.Huohuo.Huohuo.Order;
import com.Huohuo.Huohuo.OrderAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentHomeMessageBinding;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeMessageFragment extends BaseFragment<FragmentHomeMessageBinding> {

    private SwipeRefreshLayout swipeRefreshLayout;
    private OrderAdapter adapter;

    private List<Order> orderList = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
        initOrder();
        initRecycleView();
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_message;
    }

    private void initView() {
        Banner banner = bindingView.banner;
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
                                showContentView();
                                initView();
                                initOrder();
                                initRecycleView();
                                loadData();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
            }
        });
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);
    }

    private void initOrder(){
        for(int i = 0; i < 2 ; i ++){
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
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}