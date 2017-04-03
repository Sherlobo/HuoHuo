package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.ActivityOrderInfoBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

/**
 * Created by Tony on 2017/3/4.
 */

public class OrderInfoActivity extends BaseActivity <ActivityOrderInfoBinding> implements View.OnClickListener {

    private TextView startTime;
    private TextView status;
    private TextView starting;
    private TextView destination;
    private TextView goodsInfo;
    private TextView truckInfo;
    private TextView remark;
    private TextView mileageEst;
    private TextView priceEst;
    private LinearLayout llExtend;
    private Button extend;
    private Button confirm;
    private Button map;

    private OrderForm orderForm;
    private String strTruckInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        showContentView();
        setTitle("订单信息");
        initView();
        initTruck();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, OrderInfoActivity.class);
        context.startActivity(intent);
    }

    private void initTruck() {
        AVQuery query = new AVQuery("Truck");
        query.getInBackground(orderForm.getTruckId(), new GetCallback() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    strTruckInfo = avObject.get("type").toString();
                    truckInfo.setText(strTruckInfo);
                }
            }

            @Override
            protected void internalDone0(Object o, AVException e) {

            }
        });
    }

    private void initView() {
        startTime = bindingView.startTime;
        status = bindingView.status;
        starting = bindingView.starting;
        destination = bindingView.destination;
        goodsInfo = bindingView.goodsInfo;
        truckInfo = bindingView.truckInfo;
        remark = bindingView.remark;
        mileageEst = bindingView.mileageEst;
        priceEst = bindingView.priceEst;
        llExtend = bindingView.llExtend;
        extend = bindingView.extend;
        confirm = bindingView.confirm;
        map = bindingView.map;
        extend.setOnClickListener(this);
        confirm.setOnClickListener(this);
        map.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orderForm = (OrderForm) bundle.getSerializable("orderForm");
        String weight = orderForm.getWeight();
        startTime.setText("发货时间  " + orderForm.getStartTime());
        starting.setText(orderForm.getStarting());
        destination.setText(orderForm.getDestination());
        goodsInfo.setText("货物信息：  " + weight + " " + orderForm.getTypeOfGoods());
        remark.setText("备注： " + orderForm.getRemark());
        if (orderForm.getStatus() == OrderForm.FINISHED) {
            mileageEst.setText("里程： " + Double.toString(orderForm.getMile()) + "公里");
            priceEst.setText("费用： " + Double.toString(orderForm.getPrice()) + "元");
        } else {
            mileageEst.setText("预估里程： " + Double.toString(orderForm.getMile()) + "公里");
            priceEst.setText("预估费用： " + Double.toString(orderForm.getPrice()) + "元");
        }
        switch (orderForm.getStatus()) {
            case OrderForm.PENDING:
                status.setText("待接单");
                confirm.setText("取消订单");
                extend.setVisibility(View.GONE);
                break;
            case OrderForm.UNDERWAY:
                status.setText("已取货");
                llExtend.setVisibility(View.GONE);
                break;
            case OrderForm.WAITING:
                status.setText("进行中");
                llExtend.setVisibility(View.GONE);
                break;
            case OrderForm.UNCONFIRMED:
                status.setText("待收货");
                confirm.setText("确认收货");
                extend.setText("延长收货");
                break;
            case OrderForm.FINISHED:
                status.setText("已完成");
                confirm.setText("评价");
                extend.setVisibility(View.GONE);
                break;
            default:
                break;
        }
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
                        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
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
                    } else {
                        Toast.makeText(OrderInfoActivity.this, "请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                switch (orderForm.getStatus()) {
                    case OrderForm.PENDING:
                        break;
                    case OrderForm.UNCONFIRMED:
                        send(orderForm);
                        finish();
                        break;
                    case OrderForm.FINISHED:
                        break;
                }
                break;
            case R.id.extend:
                break;
            case R.id.map:
                MapActivity.start(OrderInfoActivity.this);
                break;
        }
    }

}
