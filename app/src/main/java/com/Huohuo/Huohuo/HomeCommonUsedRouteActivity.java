package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedRouteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedRouteActivity extends BaseActivity<ActivityHomeCommonUsedRouteBinding> {

    private List<Route> routeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_common_used_route);
        showContentView();
        setTitle("常用路线");
        initRoutes();
        initRecycleView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedRouteActivity.class);
        context.startActivity(intent);
    }

    private void initRoutes() {
        for (int i = 0; i < 2; i ++) {
            Route routeA = new Route("五号大街", "王大锤", "四号大街", "王小锤");
            routeList.add(routeA);
            Route routeB = new Route("六号大街", "王大锤", "五号大街", "王小锤");
            routeList.add(routeB);
        }
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RouteAdapter adapter = new RouteAdapter(routeList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RouteAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Route r) {
                Route route = r;
                String strShipper = route.getShipper();
                String strStarting = route.getStarting();
                String strReceiver = route.getReceiver();
                String strDestination = route.getDestination();
                Intent intent = new Intent();
                intent.putExtra("data_shipper", strShipper);
                intent.putExtra("data_starting", strStarting);
                intent.putExtra("data_receiver", strReceiver);
                intent.putExtra("data_destination", strDestination);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home_common_used, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                AddNewRouteActivity.start(HomeCommonUsedRouteActivity.this);
                break;
        }
        return true;
    }

}
