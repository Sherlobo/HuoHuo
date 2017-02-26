package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.yqhok.project_2.R;
import com.yqhok.project_2.databinding.ActivityNavSettingsBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavSettingsActivity extends BaseActivity<ActivityNavSettingsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_settings);
        showContentView();
        setTitle("设置");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavSettingsActivity.class);
        context.startActivity(intent);
    }
}
