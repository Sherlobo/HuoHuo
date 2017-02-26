package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityHomeCommonUsedTruckBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class HomeCommonUsedTruckActivity extends BaseActivity<ActivityHomeCommonUsedTruckBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_common_used_truck);
        showContentView();
        setTitle("常用车型");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeCommonUsedTruckActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home_common_used, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                AddNewTruckActivity.start(HomeCommonUsedTruckActivity.this);
                break;
        }
        return true;
    }
}
