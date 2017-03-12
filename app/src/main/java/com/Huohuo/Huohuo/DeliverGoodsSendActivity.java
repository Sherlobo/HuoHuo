package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsSendBinding;

import retrofit2.Retrofit;


/**
 * Created by yqhok on 2017-02-25.
 */

public class DeliverGoodsSendActivity extends BaseActivity<ActivityDeliverGoodsSendBinding> implements View.OnClickListener {

    private Button button;
    private Order order;

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

    private void initView() {
        button = bindingView.sendOrder;
        button.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        order = (Order) bundle.getSerializable("order");
        bindingView.time.setText(order.getTime());
        bindingView.shipper.setText(order.getShipper());
        bindingView.starting.setText(order.getStarting());
        bindingView.receiver.setText(order.getReceiver());
        bindingView.destination.setText(order.getDestination());
        bindingView.goodsInfo.setText(order.getWeight() + "     " + order.getTypeOfGoods());
        bindingView.remark.setText(order.getRemark());
    }

    private void send(Order o) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080").build();
        Order order = o;
        order.save();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_order:
                if (order != null) {
                    send(order);
                }
                MainActivity.start(DeliverGoodsSendActivity.this);
                break;
            default:
                break;
        }
    }
}
