package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityNavWalletBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class NavWalletActivity extends BaseActivity<ActivityNavWalletBinding> implements View.OnClickListener {
    private Button mBill;
    private Button mBandCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_wallet);
        showContentView();
        setTitle("钱包");
        initId();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NavWalletActivity.class);
        context.startActivity(intent);
    }
    private void initId(){
        mBill=bindingView.Bill;
        mBandCard=bindingView.BandCard;
        mBill.setOnClickListener( this);
        mBandCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Bill:
                WalletBillActivty.start(NavWalletActivity.this);
                break;
            case R.id.BandCard:
                WalletBandCardActivity.start(NavWalletActivity.this);
                break;
            default:
                break;
        }
    }
}
