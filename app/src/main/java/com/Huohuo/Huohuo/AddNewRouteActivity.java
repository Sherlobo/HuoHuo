package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityAddNewRouteBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class AddNewRouteActivity extends BaseActivity<ActivityAddNewRouteBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_route);
        showContentView();
        setTitle("新增路线");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNewRouteActivity.class);
        context.startActivity(intent);
    }
}
