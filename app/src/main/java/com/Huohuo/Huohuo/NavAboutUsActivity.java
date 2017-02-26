package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityNavAboutUsBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavAboutUsActivity extends BaseActivity<ActivityNavAboutUsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_about_us);
        showContentView();
        setTitle("关于我们");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavAboutUsActivity.class);
        context.startActivity(intent);
    }
}
