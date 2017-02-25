package com.yqhok.project_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yqhok.project_2.base.BaseActivity;
import com.yqhok.project_2.databinding.ActivityNavRecommendFriendsBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavRecommendFriendsActivity extends BaseActivity<ActivityNavRecommendFriendsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_recommend_friends);
        showContentView();
        setTitle("推荐好友");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavRecommendFriendsActivity.class);
        context.startActivity(intent);
    }
}
