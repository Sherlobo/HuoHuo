package com.Huohuo.Huohuo.order.child;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.OrderEvaluateActivity;
import com.Huohuo.Huohuo.OrderInfoActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.OrderAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.FragmentOrderFinishedBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderFinishedFragment extends BaseFragment<FragmentOrderFinishedBinding> {

    private List<OrderForm> orderFormList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
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
                new Update().execute();
            }
        });
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
                        initDriver(orderForm);
                    }
                }
            });
        }
        orderFormList.clear();
        for (OrderForm orderForm : orderList) {
            if (orderForm.getStatus() == OrderForm.FINISHED) {
                orderFormList.add(orderForm);
            }
        }
    }

    private void initDriver(final OrderForm orderForm) {
        AVQuery<AVObject> query = new AVQuery<>("Driver");
        query.getInBackground(orderForm.getDriverId(), new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    Driver driver = new Driver();
                    driver.setObjectId(avObject.getObjectId());
                    driver.setPhone(avObject.get("phone").toString());
                    driver.setRealName(avObject.get("realName").toString());
                    driver.setIdNumber(avObject.get("idNumber").toString());
                    driver.setTaskCount(Integer.parseInt(avObject.get("taskCount").toString()));
                    driver.setRating(Float.parseFloat(avObject.get("rating").toString()));
                    driver.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                    orderForm.setDriver(driver);
                    orderForm.save();
                }
            }
        });
    }

    private void initRecycleView() {
        recyclerView = bindingView.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(orderFormList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OrderAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, OrderForm orderForm) {
                Intent intent;
                Bundle bundle = new Bundle();
                switch (view.getId()) {
                    case R.id.confirm:
                        intent = new Intent(getActivity(), OrderEvaluateActivity.class);
                        bundle.putSerializable("orderForm", orderForm);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.order_card:
                        intent = new Intent(getActivity(), OrderInfoActivity.class);
                        bundle.putSerializable("orderForm", orderForm);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }
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
        new Update().execute();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
