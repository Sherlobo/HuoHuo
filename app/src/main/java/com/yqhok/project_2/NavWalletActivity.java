package com.yqhok.project_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yqhok.project_2.base.BaseActivity;
import com.yqhok.project_2.databinding.ActivityNavWalletBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavWalletActivity extends BaseActivity<ActivityNavWalletBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_wallet);

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavWalletActivity.class);
        context.startActivity(intent);
    }
}
