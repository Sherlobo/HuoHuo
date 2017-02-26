package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityNavQrcodeBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavQrcodeActivity extends BaseActivity<ActivityNavQrcodeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_qrcode);
        showContentView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavQrcodeActivity.class);
        context.startActivity(intent);
    }
}
