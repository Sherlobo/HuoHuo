package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityAddNewTruckBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class AddNewTruckActivity extends BaseActivity<ActivityAddNewTruckBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_truck);
        showContentView();
        setTitle("新增车型");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNewTruckActivity.class);
        context.startActivity(intent);
    }
}
