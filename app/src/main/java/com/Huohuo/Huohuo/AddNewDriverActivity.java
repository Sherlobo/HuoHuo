package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityAddNewDriverBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class AddNewDriverActivity extends BaseActivity<ActivityAddNewDriverBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_truck);
        showContentView();
        setTitle("新增司机");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNewDriverActivity.class);
        context.startActivity(intent);
    }
}
