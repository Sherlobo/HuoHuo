package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityWalletSetpwBinding;

/**
 * Created by Tony on 2017/3/5.
 */

public class WalletSetpwActivity extends BaseActivity<ActivityWalletSetpwBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_setpw);
        showContentView();
        setTitle("修改密码");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WalletSetpwActivity.class);
        context.startActivity(intent);
    }
}
