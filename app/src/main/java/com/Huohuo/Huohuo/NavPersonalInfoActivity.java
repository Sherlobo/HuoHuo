package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityNavPersonalInfoBinding;

/**
 * Created by yqhok on 2017-02-25.
 */

public class NavPersonalInfoActivity extends BaseActivity<ActivityNavPersonalInfoBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_personal_info);
        showContentView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavPersonalInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_person_info, menu);
        return true;
    }
}
