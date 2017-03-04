package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityWalletBillBinding;

/**
 * Created by Tony on 2017/2/26.
 */

public class WalletBillActivty extends BaseActivity<ActivityWalletBillBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_bill);
        showContentView();
        setTitle("账单");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WalletBillActivty.class);
        context.startActivity(intent);
    }
}
