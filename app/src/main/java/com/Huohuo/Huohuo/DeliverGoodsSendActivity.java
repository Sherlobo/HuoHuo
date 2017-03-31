package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsSendBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;


/**
 * Created by yqhok on 2017-02-25.
 */

public class DeliverGoodsSendActivity extends BaseActivity<ActivityDeliverGoodsSendBinding> implements View.OnClickListener {

    private Button button;
    private OrderForm orderForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_deliver_goods_send);
        super.onCreate(savedInstanceState);
        showContentView();
        setTitle("订单");
        initView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsSendActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_deliver_goods, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fee_standard:
                FeeScaleActivity.start(DeliverGoodsSendActivity.this);
            default:
        }
        return true;
    }


    private void initView() {
        button = bindingView.sendOrder;
        button.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orderForm = (OrderForm) bundle.getSerializable("orderForm");
        bindingView.time.setText(orderForm.getStartTime());
        bindingView.shipper.setText(orderForm.getShipper());
        bindingView.starting.setText(orderForm.getStarting());
        bindingView.receiver.setText(orderForm.getReceiver());
        bindingView.destination.setText(orderForm.getDestination());
        bindingView.goodsInfo.setText(orderForm.getWeight() + "     " + orderForm.getTypeOfGoods());
        bindingView.remark.setText(orderForm.getRemark());
    }

    private void send(final OrderForm o) {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String id = preferences.getString("id", "");
        final AVObject AVOrder = new AVObject("OrderForm");
        AVOrder.put("clientId", id);
        AVOrder.put("startTime", o.getStartTime());
        AVOrder.put("shipper", o.getShipper());
        AVOrder.put("starting", o.getStarting());
        AVOrder.put("receiver", o.getReceiver());
        AVOrder.put("destination", o.getDestination());
        AVOrder.put("weight", o.getWeight());
        AVOrder.put("typeOfGoods", o.getTypeOfGoods());
        AVOrder.put("truckId", o.getTruck().getObjectId());
        AVOrder.put("remark", o.getRemark());
        AVOrder.put("mile", o.getMile());
        AVOrder.put("price", o.getPrice());
        AVOrder.put("status", o.getStatus());
        AVOrder.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    o.setObjectId(AVOrder.getObjectId());
                    o.save();
                } else {
                    Toast.makeText(DeliverGoodsSendActivity.this, "操作失败，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_order:
                if (orderForm != null) {
                    send(orderForm);
                }
                MainActivity.start(DeliverGoodsSendActivity.this);
                break;
            default:
                break;
        }
    }
}
