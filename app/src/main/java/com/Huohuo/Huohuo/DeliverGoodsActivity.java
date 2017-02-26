package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.yqhok.project_2.R;
import com.yqhok.project_2.databinding.ActivityDeliverGoodsBinding;

/**
 * Created by yqhok on 2017-02-24.
 */

public class DeliverGoodsActivity extends BaseActivity<ActivityDeliverGoodsBinding> implements View.OnClickListener{

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_goods);
        showContentView();
        setTitle("发货");
        initView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_deliver_goods, menu);
        return true;
    }

    private void initView() {
        button = bindingView.send;
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                DeliverGoodsSendActivity.start(DeliverGoodsActivity.this);
                break;
            default:
                break;
        }
    }
}
