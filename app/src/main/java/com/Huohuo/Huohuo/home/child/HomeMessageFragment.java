package com.Huohuo.Huohuo.home.child;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
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
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private Banner banner;

    private List<String> urlList = new ArrayList<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initOrder();
        initView();
        initRecycleView();
    }


    @Override
    public int setContent() {
        return R.layout.fragment_home_message;
    }

    private void initOrder(){
        List<OrderForm> orderList = DataSupport.findAll(OrderForm.class);
        AVQuery<AVObject> query = new AVQuery<>("OrderForm");
        for (final OrderForm orderForm : orderList) {
            query.getInBackground(orderForm.getObjectId(), new GetCallback<AVObject>() {
                @Override
                public void done(AVObject avObject, AVException e) {
                    if (e == null) {
                        orderForm.setStatus(Integer.parseInt(avObject.get("status").toString()));
                        orderForm.save();
                    }
                }
            });
        }
        orderFormList.clear();
        for (OrderForm orderForm : orderList) {
            if (orderForm.getStatus() == OrderForm.PENDING) {
                orderFormList.add(orderForm);
            }
        }
        for (OrderForm orderForm : orderList) {
            if (orderForm.getStatus() == OrderForm.UNDERWAY) {
                orderFormList.add(orderForm);
            }
        }
        for (OrderForm orderForm : orderList) {
            if (orderForm.getStatus() == OrderForm.WAITING) {
                orderFormList.add(orderForm);
            }
        }
    }

    private void initView() {
        banner = bindingView.banner;
        urlList.add("http://image.qjwb.com.cn/group1/M00/01/1B/CggkA1fDfVSAbV7jABxuw-Jc4KE779.jpg");
        urlList.add("http://www.getdigsy.com/blog/wp-content/uploads/2016/12/industrial-hall-1630740_1280.jpg");
        urlList.add("https://www.leadbook.com/wp-content/uploads/2017/02/our-data1.jpeg");
        banner.setImages(urlList).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        swipeRefreshLayout = bindingView.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.Red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Update().execute();
            }
        });
    }

    private void initRecycleView() {
        banner = bindingView.banner;
        recyclerView = bindingView.recycleView;
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

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setFocusable(false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onRefresh() {
        new Update().execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onRefresh();
        }
    }

    class Update extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(true);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            initOrder();
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
            adapter.notifyDataSetChanged();
        }

    }

}