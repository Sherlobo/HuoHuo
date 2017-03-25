package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityAddNewRouteBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class AddNewRouteActivity extends BaseActivity<ActivityAddNewRouteBinding> implements View.OnClickListener{
    private Button button;
    private TextView setStarting;
    private TextView setEnding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_route);
        showContentView();
        setTitle("新增路线");
        initView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNewRouteActivity.class);
        context.startActivity(intent);
    }
    private void initView() {
        button = bindingView.send;
        button.setOnClickListener(this);
        setStarting = bindingView.setStarting;
        setStarting.setOnClickListener(this);
        setEnding= bindingView.setEnding;
        setEnding.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setStarting:
                break;
            case R.id.setEnding:
                break;
            case R.id.send:
            default:
                break;
        }
    }
}
