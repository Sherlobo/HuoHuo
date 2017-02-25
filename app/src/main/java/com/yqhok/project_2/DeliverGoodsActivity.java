package com.yqhok.project_2;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.yqhok.project_2.base.BaseActivity;
import com.yqhok.project_2.databinding.ActivityDeliverGoodsBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class DeliverGoodsActivity extends BaseActivity<ActivityDeliverGoodsBinding>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_goods);
        showContentView();
        setTitle("发货");
        initView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
    }
}
