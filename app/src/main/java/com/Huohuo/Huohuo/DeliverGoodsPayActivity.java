package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsPayBinding;

public class DeliverGoodsPayActivity extends BaseActivity<ActivityDeliverGoodsPayBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_goods_pay);
        setTitle("选择付款方式");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsPayActivity.class);
        context.startActivity(intent);
    }

}
