package com.yqhok.project_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yqhok.project_2.base.BaseActivity;
import com.yqhok.project_2.databinding.ActivityNavRecentlyViewedBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavRecentlyViewedActivity extends BaseActivity<ActivityNavRecentlyViewedBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_recently_viewed);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavRecentlyViewedActivity.class);
        context.startActivity(intent);
    }
}
