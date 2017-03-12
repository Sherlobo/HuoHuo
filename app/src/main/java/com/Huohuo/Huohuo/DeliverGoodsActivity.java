package com.Huohuo.Huohuo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsBinding;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by yqhok on 2017-02-24.
 */

public class DeliverGoodsActivity extends BaseActivity<ActivityDeliverGoodsBinding> implements View.OnClickListener{

    private Button button;
    private TextView setTime;
    private TextView setRoute;
    private TextView setTruck;
    private TextView setDriver;
    private TextView setGoodsInfo;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private StringBuilder time = null;
    private int year, month, day;
    private Calendar calendar;

    private String strShipper = null;
    private String strStarting = null;
    private String strReceiver = null;
    private String strDestination = null;
    private String strWeight = null;
    private String typeOfGoods = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        setTime = bindingView.setTime;
        setTime.setOnClickListener(this);
        setRoute = bindingView.setRoute;
        setRoute.setOnClickListener(this);
        setTruck = bindingView.setTruck;
        setTruck.setOnClickListener(this);
        setDriver = bindingView.setDriver;
        setDriver.setOnClickListener(this);
        setGoodsInfo = bindingView.setGoodsInfo;
        setGoodsInfo.setOnClickListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                if (time == null || time.toString().isEmpty()) {
                    Toast.makeText(DeliverGoodsActivity.this, "请选择发货时间", Toast.LENGTH_SHORT).show();
                } else if (strShipper == null || strShipper.isEmpty()) {
                    Toast.makeText(DeliverGoodsActivity.this, "请选择路线", Toast.LENGTH_SHORT).show();
                //} else if (setTruck.getText() == null) {
                //    Toast.makeText(DeliverGoodsActivity.this, "请选择车型", Toast.LENGTH_SHORT).show();
                } else if (strWeight == null || strWeight.isEmpty() || typeOfGoods == null || typeOfGoods.isEmpty()) {
                    Toast.makeText(DeliverGoodsActivity.this, "请选择货物信息", Toast.LENGTH_SHORT).show();
                } else {
                    String remark = "";
                    if (bindingView.remark.getText() != null && bindingView.remark.getText().equals("")) {
                        remark = bindingView.remark.getText().toString();
                    }
                    Order order = new Order(time.toString(), strShipper, strStarting, strReceiver, strDestination, strWeight, typeOfGoods, remark, Order.UNDERWAY);
                    Intent intent = new Intent(DeliverGoodsActivity.this, DeliverGoodsSendActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("order", order);
                    intent.putExtras(bundle);
                    this.startActivity(intent);
                }
                break;
            case R.id.setTime:
                time = new StringBuilder();
                timePickerDialog = new TimePickerDialog(DeliverGoodsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String strHourOfDay = i < 10 ? "0" + i : "" + i;
                        String strMinuteOfDay = i1 < 10 ? "0" + i1 : "" + i1;
                        time.append(strHourOfDay + ":" + strMinuteOfDay);
                        setTime.setText(time);
                    }
                }, 0, 0 ,true);
                timePickerDialog.show();
                datePickerDialog = new DatePickerDialog(DeliverGoodsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        time.setLength(0);
                        time.append(String.format(Locale.CHINA, "%d-%d-%d", i, i1 + 1, i2) + "  ");
                    }
                }, year, month, day);
                datePickerDialog.show();
                break;
            case R.id.setRoute:
                Intent intentToRoute = new Intent(DeliverGoodsActivity.this, HomeCommonUsedRouteActivity.class);
                intentToRoute.putExtra("handle", "set");
                startActivityForResult(intentToRoute, 1);
                break;
            case R.id.setTruck:
                Intent intentToTruck = new Intent(DeliverGoodsActivity.this, HomeCommonUsedTruckActivity.class);
                intentToTruck.putExtra("handle", "set");
                startActivityForResult(intentToTruck, 2);
                break;
            case R.id.setDriver:
                Intent intentToDriver = new Intent(DeliverGoodsActivity.this, HomeCommonUsedDriverActivity.class);
                intentToDriver.putExtra("handle", "set");
                startActivityForResult(intentToDriver, 3);
                break;
            case R.id.setGoodsInfo:
                Intent intentToGoodsInfo = new Intent(DeliverGoodsActivity.this, DeliverGoodsInfoActivity.class);
                intentToGoodsInfo.putExtra("handle", "set");
                startActivityForResult(intentToGoodsInfo, 4);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    strShipper = data.getStringExtra("data_shipper");
                    strStarting = data.getStringExtra("data_starting");
                    strReceiver = data.getStringExtra("data_receiver");
                    strDestination = data.getStringExtra("data_destination");
                    String strRoute = strShipper + ":" + strStarting + "————" + strReceiver + ":" + strDestination;
                    setRoute.setText(strRoute);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    setTruck.setText(returnedData);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    setDriver.setText(returnedData);
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    strWeight = data.getStringExtra("data_weight");
                    typeOfGoods = data.getStringExtra("type_of_goods");
                    setGoodsInfo.setText(strWeight + " " + typeOfGoods);
                }
                break;
        }
    }
}
