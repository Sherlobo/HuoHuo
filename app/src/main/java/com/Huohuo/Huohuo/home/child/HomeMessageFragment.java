package com.Huohuo.Huohuo.home.child;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.Huohuo.Huohuo.databinding.FragmentHomeMessageBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.youth.banner.Banner;

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
        initDb();
        initOrder();
        initView();
        initRecycleView();
    }


    @Override
    public int setContent() {
        return R.layout.fragment_home_message;
    }

    private void initDb() {
        List<OrderForm> list = DataSupport.findAll(OrderForm.class);
        AVQuery<AVObject> query = new AVQuery<>("OrderForm");
        for (final OrderForm orderForm : list) {
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
    }

    private void initView() {
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
        orderFormList.clear();
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
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            initOrder();
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            swipeRefreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }

    }

}