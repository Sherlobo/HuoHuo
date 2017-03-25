package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityFeeScaleBinding;

/**
 * Created by JinBo on 2017/3/21.
 */

public class FeeScaleActivity extends BaseActivity<ActivityFeeScaleBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_scale);
        showContentView();
        setTitle("收费标准");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, FeeScaleActivity.class);
        context.startActivity(intent);
    }
}
