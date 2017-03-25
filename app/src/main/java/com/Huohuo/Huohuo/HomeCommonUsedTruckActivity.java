package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.Huohuo.Huohuo.adapter.TruckAdapter;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Truck;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedTruckBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedTruckActivity extends BaseActivity<ActivityHomeCommonUsedTruckBinding> {
    private List<Truck> mTruckList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_common_used_truck);
        showContentView();
        initTruck();
        initRecyclerView();
        setTitle("常用车型");
    }

    private void initRecyclerView() {
        RecyclerView recyclerView=bindingView.recycleView;
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TruckAdapter adapter=new TruckAdapter(mTruckList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TruckAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Truck truck) {
                Intent intent=getIntent();
                if (intent.getStringExtra("handle").equals("set")) {
                    intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("truck", truck);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void initTruck() {
        Truck truck1=new Truck("小件送","3公斤内","12元起","0.3*0.3*0.2m","2元");
        mTruckList.add(truck1);
        Truck truck2=new Truck("面包车","1.5吨","40元起","1.7*1.2*1m","3.5元");
        mTruckList.add(truck2);
        Truck truck3=new Truck("金杯车","1.5吨","68元起","2，7*1.3*1.1m","5元");
        mTruckList.add(truck3);
        Truck truck4=new Truck("依维柯","1.6吨","88元起","2.5*1.9*1.3m","5元");
        mTruckList.add(truck4);
        Truck truck5=new Truck("小型货车","1.5吨","88元起","2.8*1.6*1.5m","6元");
        mTruckList.add(truck5);
        Truck truck6=new Truck("中型货车","1.8吨","158元起","4.2*1.8*1.8m","7元");
        mTruckList.add(truck6);

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedTruckActivity.class);
        context.startActivity(intent);
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
                AddNewTruckActivity.start(HomeCommonUsedTruckActivity.this);
                break;
        }
        return true;
    }
}
