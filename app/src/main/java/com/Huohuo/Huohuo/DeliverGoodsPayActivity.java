package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsPayBinding;

/**
 * Created by yqhok on 2017-02-25.
 */

public class DeliverGoodsPayActivity extends BaseActivity<ActivityDeliverGoodsPayBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_goods_pay);
        showContentView();
        setTitle("费用支付");
        initView();
;    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsPayActivity.class);
        context.startActivity(intent);
    }

    private void initView() {

    }

}
