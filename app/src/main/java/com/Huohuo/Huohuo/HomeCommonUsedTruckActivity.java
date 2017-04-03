package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.adapter.TruckAdapter;
import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Truck;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedTruckBinding;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedTruckActivity extends BaseActivity<ActivityHomeCommonUsedTruckBinding> {

    private List<Truck> truckList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TruckAdapter adapter;

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
        recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TruckAdapter(truckList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TruckAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Truck truck) {
                Intent intent = getIntent();
                if (intent.getStringExtra("handle") != null && intent.getStringExtra("handle").equals("set")) {
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
        DataSupport.deleteAll(Truck.class);
        Truck truck1 = new Truck();
        truck1.setObjectId("58d904caa22b9d00646e595f");
        truck1.setImageId(R.drawable.truck_order_1);
        truck1.setType("小型货车");
        truck1.setSize("2.8*1.6*1.5m");
        truck1.setInicost(88);
        truck1.setOvercost(6);
        truck1.setWeight("1.5吨");
        truck1.save();
        Truck truck2 = new Truck();
        truck2.setObjectId("58d904f30ce463005710e821");
        truck2.setImageId(R.drawable.truck_order_2);
        truck2.setType("中型货车");
        truck2.setSize("4.2*1.8*1.8m");
        truck2.setInicost(158);
        truck2.setOvercost(7);
        truck2.setWeight("1.8吨");
        truck2.save();
        Truck truck3 = new Truck();
        truck3.setObjectId("58d904ad61ff4b006cd745d2");
        truck3.setImageId(R.drawable.truck_order_3);
        truck3.setType("依维柯");
        truck3.setSize("2.5*1.9*1.3m");
        truck3.setInicost(88);
        truck3.setOvercost(5);
        truck3.setWeight("1.6吨");
        truck3.save();
        Truck truck4 = new Truck();
        truck4.setObjectId("58d9047544d90400687d691d");
        truck4.setImageId(R.drawable.truck_order_4);
        truck4.setType("金杯车");
        truck4.setSize("2.7*1.3*1.1m");
        truck4.setInicost(68);
        truck4.setOvercost(5);
        truck4.setWeight("1.5吨");
        truck4.save();
        Truck truck5 = new Truck();
        truck5.setObjectId("58d9038761ff4b006cd73c50");
        truck5.setImageId(R.drawable.truck_order_5);
        truck5.setType("面包车");
        truck5.setSize("1.7*1.2*1m");
        truck5.setInicost(40);
        truck5.setOvercost(3.5);
        truck5.setWeight("3公斤内");
        truck5.save();
        Truck truck6 = new Truck();
        truck6.setObjectId("58d903940ce463005710def1");
        truck6.setImageId(R.drawable.truck_order_6);
        truck6.setType("小件送");
        truck6.setSize("0.3*0.3*0.2m");
        truck6.setInicost(12);
        truck6.setOvercost(2);
        truck6.setWeight("1.5吨");
        truck6.save();
        truckList.clear();
        truckList.add(truck1);
        truckList.add(truck2);
        truckList.add(truck3);
        truckList.add(truck4);
        truckList.add(truck5);
        truckList.add(truck6);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedTruckActivity.class);
        context.startActivity(intent);
    }

}
