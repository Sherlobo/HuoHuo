package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityAddNewTruckBinding;

/**
 * Created by yqhok on 2017-02-26.
 */

public class AddNewTruckActivity extends BaseActivity<ActivityAddNewTruckBinding> implements View.OnClickListener {

    private Button button;
    private TextView setTruck;
    private TextView remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_truck);
        showContentView();
        setTitle("新增车型");
        initView();
    }

    private void initView() {
        button = bindingView.confirm;
        button.setOnClickListener(this);
        setTruck = bindingView.setTruck;
        setTruck.setOnClickListener(this);
        remark = bindingView.remark;
        remark.setOnClickListener(this);

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AddNewTruckActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setTruck:
                Intent intentToTruck = new Intent(AddNewTruckActivity.this, HomeCommonUsedTruckActivity.class);
                intentToTruck.putExtra("handle", "set");
                startActivityForResult(intentToTruck, 2);
                break;
            case R.id.remark:
                break;
            case R.id.confirm:
                break;
            default:
                break;
        }
    }
}
