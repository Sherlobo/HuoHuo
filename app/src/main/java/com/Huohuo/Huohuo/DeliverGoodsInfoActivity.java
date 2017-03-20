package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Spinner;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsInfoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-03-08.
 */

public class DeliverGoodsInfoActivity extends BaseActivity<ActivityDeliverGoodsInfoBinding> implements View.OnClickListener {

    private Spinner spinner;
    private Button confirm;

    private List<CheckedTextView> checkedTextViewList;

    private String strWeight;
    private StringBuilder typeOfGoods;
    private CheckedTextView type_1;
    private CheckedTextView type_2;
    private CheckedTextView type_3;
    private CheckedTextView type_4;
    private CheckedTextView type_5;
    private CheckedTextView type_6;
    private CheckedTextView type_7;
    private CheckedTextView type_8;
    private CheckedTextView type_9;
    private CheckedTextView type_10;
    private CheckedTextView type_11;
    private CheckedTextView type_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_goods_info);
        showContentView();
        setTitle("选择货物信息");
        initView();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, DeliverGoodsInfoActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        confirm = bindingView.confirm;
        confirm.setOnClickListener(this);
        spinner = bindingView.spinner;
        checkedTextViewList = new ArrayList<>();
        type_1 = bindingView.type1;
        checkedTextViewList.add(type_1);
        type_1.setOnClickListener(this);
        type_2 = bindingView.type2;
        checkedTextViewList.add(type_2);
        type_2.setOnClickListener(this);
        type_3 = bindingView.type3;
        checkedTextViewList.add(type_3);
        type_3.setOnClickListener(this);
        type_4 = bindingView.type4;
        checkedTextViewList.add(type_4);
        type_4.setOnClickListener(this);
        type_5 = bindingView.type5;
        checkedTextViewList.add(type_5);
        type_5.setOnClickListener(this);
        type_6 = bindingView.type6;
        checkedTextViewList.add(type_6);
        type_6.setOnClickListener(this);
        type_7 = bindingView.type7;
        checkedTextViewList.add(type_7);
        type_7.setOnClickListener(this);
        type_8 = bindingView.type8;
        checkedTextViewList.add(type_8);
        type_8.setOnClickListener(this);
        type_9 = bindingView.type9;
        checkedTextViewList.add(type_9);
        type_9.setOnClickListener(this);
        type_10 = bindingView.type10;
        checkedTextViewList.add(type_10);
        type_10.setOnClickListener(this);
        type_11 = bindingView.type11;
        checkedTextViewList.add(type_11);
        type_11.setOnClickListener(this);
        type_12 = bindingView.type12;
        checkedTextViewList.add(type_12);
        type_12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                Intent intent = new Intent();
                typeOfGoods = new StringBuilder();
                strWeight = spinner.getSelectedItem().toString();
                for (CheckedTextView checkedTextView : checkedTextViewList) {
                    if (checkedTextView.isChecked()) {
                        typeOfGoods.append(checkedTextView.getText() + "/");
                    }
                }
                if (typeOfGoods.toString() != "") {
                    typeOfGoods.deleteCharAt(typeOfGoods.length() - 1);
                }
                intent.putExtra("type_of_goods", typeOfGoods.toString());
                intent.putExtra("weight", strWeight);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.type_1:
                if (type_1.isChecked()) {
                    type_1.setChecked(false);
                    type_1.setBackgroundResource(R.color.LightGray);
                } else {
                    type_1.setChecked(true);
                    type_1.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_2:
                if (type_2.isChecked()) {
                    type_2.setChecked(false);
                    type_2.setBackgroundResource(R.color.LightGray);
                } else {
                    type_2.setChecked(true);
                    type_2.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_3:
                if (type_3.isChecked()) {
                    type_3.setChecked(false);
                    type_3.setBackgroundResource(R.color.LightGray);
                } else {
                    type_3.setChecked(true);
                    type_3.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_4:
                if (type_4.isChecked()) {
                    type_4.setChecked(false);
                    type_4.setBackgroundResource(R.color.LightGray);
                } else {
                    type_4.setChecked(true);
                    type_4.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_5:
                if (type_5.isChecked()) {
                    type_5.setChecked(false);
                    type_5.setBackgroundResource(R.color.LightGray);
                } else {
                    type_5.setChecked(true);
                    type_5.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_6:
                if (type_6.isChecked()) {
                    type_6.setChecked(false);
                    type_6.setBackgroundResource(R.color.LightGray);
                } else {
                    type_6.setChecked(true);
                    type_6.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_7:
                if (type_7.isChecked()) {
                    type_7.setChecked(false);
                    type_7.setBackgroundResource(R.color.LightGray);
                } else {
                    type_7.setChecked(true);
                    type_7.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_8:
                if (type_8.isChecked()) {
                    type_8.setChecked(false);
                    type_8.setBackgroundResource(R.color.LightGray);
                } else {
                    type_8.setChecked(true);
                    type_8.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_9:
                if (type_9.isChecked()) {
                    type_9.setChecked(false);
                    type_9.setBackgroundResource(R.color.LightGray);
                } else {
                    type_9.setChecked(true);
                    type_9.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_10:
                if (type_10.isChecked()) {
                    type_10.setChecked(false);
                    type_10.setBackgroundResource(R.color.LightGray);
                } else {
                    type_10.setChecked(true);
                    type_10.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_11:
                if (type_11.isChecked()) {
                    type_11.setChecked(false);
                    type_11.setBackgroundResource(R.color.LightGray);
                } else {
                    type_11.setChecked(true);
                    type_11.setBackgroundResource(R.color.Red);
                }
                break;
            case R.id.type_12:
                if (type_12.isChecked()) {
                    type_12.setChecked(false);
                    type_12.setBackgroundResource(R.color.LightGray);
                } else {
                    type_12.setChecked(true);
                    type_12.setBackgroundResource(R.color.Red);
                }
                break;
            default:
                break;
        }
    }

}
