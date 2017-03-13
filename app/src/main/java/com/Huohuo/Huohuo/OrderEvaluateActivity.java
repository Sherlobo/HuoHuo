package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityOrderEvaluateBinding;

/**
 * Created by Tony on 2017/3/11.
 */

public class OrderEvaluateActivity extends BaseActivity <ActivityOrderEvaluateBinding>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_evaluate);
        showContentView();
        setTitle("评价");
    }
    public static void start(Context context) {
        Intent intent = new Intent(context, OrderEvaluateActivity.class);
        context.startActivity(intent);
    }
}
