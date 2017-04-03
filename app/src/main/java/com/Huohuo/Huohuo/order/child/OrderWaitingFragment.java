package com.Huohuo.Huohuo.order.child;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.Huohuo.Huohuo.OrderInfoActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.OrderAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.FragmentOrderWaitingBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yqhok on 2017-02-23.
 */

public class OrderWaitingFragment extends BaseFragment<FragmentOrderWaitingBinding> {

    private List<OrderForm> orderFormList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;

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
        initView();
        initOrder();
        initRecycleView();
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
                        orderForm.save();
                    }
                }
            });
        }
        orderFormList.clear();
        for (OrderForm orderForm : orderList) {
            if (orderForm.getStatus() == OrderForm.WAITING || orderForm.getStatus() == OrderForm.UNCONFIRMED) {
                orderFormList.add(orderForm);
            }
        }
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
                switch (view.getId()) {
                    case R.id.confirm:
                        send(orderForm);
                        onRefresh();
                        break;
                    case R.id.extend:
                        break;
                    case R.id.order_card:
                        Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
                        Bundle bundle = new Bundle();
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
    public void onDestroy() {
        super.onDestroy();
    }

    private void send(final OrderForm orderForm) {
        AVQuery query = new AVQuery("OrderForm");
        query.getInBackground(orderForm.getObjectId(), new GetCallback() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    if (Integer.parseInt(avObject.get("status").toString()) == OrderForm.UNCONFIRMED) {
                        avObject.put("status", OrderForm.FINISHED);
                        avObject.saveInBackground();
                        orderForm.setStatus(OrderForm.FINISHED);
                        SharedPreferences preferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
                        String id = preferences.getString("id", "");
                        if (!id.isEmpty()) {
                            AVQuery<AVObject> avQuery = new AVQuery<>("Client");
                            avQuery.getInBackground(id, new GetCallback<AVObject>() {
                                @Override
                                public void done(AVObject avObject, AVException e) {
                                    if (e == null) {
                                        avObject.put("orderCount", Integer.parseInt(avObject.get("orderCount").toString()) + 1);
                                        avObject.put("points", Integer.parseInt(avObject.get("points").toString()) + 50);
                                        avObject.saveInBackground();
                                    } else {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        orderForm.save();
                        onRefresh();
                    } else {
                        Toast.makeText(getActivity(), "请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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


