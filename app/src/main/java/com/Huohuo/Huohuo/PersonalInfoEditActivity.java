package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityPersonalInfoEditBinding;

/**
 * Created by yqhok on 2017-03-14.
 */

public class PersonalInfoEditActivity extends BaseActivity<ActivityPersonalInfoEditBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_edit);
        showContentView();
        initView();
    }

    private void initView() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonalInfoEditActivity.class);
        context.startActivity(intent);
    }
}
