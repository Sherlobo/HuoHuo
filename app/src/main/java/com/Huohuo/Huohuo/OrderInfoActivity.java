package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityOrderInfoBinding;
import com.Huohuo.Huohuo.order.child.OrderWaitingFragment;

/**
 * Created by Tony on 2017/3/4.
 */

public class OrderInfoActivity extends BaseActivity <ActivityOrderInfoBinding>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        showContentView();
        setTitle("订单信息");
    }
    public static void start(Context context) {
        Intent intent = new Intent(context, OrderWaitingFragment.class);
        context.startActivity(intent);
    }
}
