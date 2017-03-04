package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityWalletBandcardBinding;

/**
 * Created by Tony on 2017/2/26.
 */

public class WalletBandCardActivity extends BaseActivity<ActivityWalletBandcardBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_bandcard);
        showContentView();
        setTitle("银行卡");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WalletBandCardActivity.class);
        context.startActivity(intent);
    }
}

