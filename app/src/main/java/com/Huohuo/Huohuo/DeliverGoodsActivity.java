package com.Huohuo.Huohuo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.bean.Route;
import com.Huohuo.Huohuo.bean.Truck;
import com.Huohuo.Huohuo.databinding.ActivityDeliverGoodsBinding;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by yqhok on 2017-02-24.
 */

public class  DeliverGoodsActivity extends BaseActivity<ActivityDeliverGoodsBinding> implements TextWatcher, View.OnClickListener{

    private Button button;
    private TextView setTime;
    private TextView setRoute;
    private TextView setTruck;
    private TextView setGoodsInfo;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private StringBuilder time = null;
    private int year, month, day;
    private Calendar calendar;

    private Route route;
    private Truck truck;

    private String strShipper;
    private String strStarting;
    private String strReceiver;
    private String strDestination;
    private String strWeight = null;
    private String typeOfGoods = null;
    private String strRemark = "";

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fee_standard:
                FeeScaleActivity.start(DeliverGoodsActivity.this);
            default:
        }
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
                } else if (setTruck.getText() == null) {
                    Toast.makeText(DeliverGoodsActivity.this, "请选择车型", Toast.LENGTH_SHORT).show();
                } else if (typeOfGoods == null || typeOfGoods.isEmpty()) {
                    Toast.makeText(DeliverGoodsActivity.this, "请选择货物信息", Toast.LENGTH_SHORT).show();
                } else {
                    OrderForm orderForm = new OrderForm();
                    orderForm.setStartTime(time.toString());
                    orderForm.setShipper(strShipper);
                    orderForm.setStarting(strStarting);
                    orderForm.setReceiver(strReceiver);
                    orderForm.setDestination(strDestination);
                    orderForm.setWeight(strWeight);
                    orderForm.setTypeOfGoods(typeOfGoods);
                    orderForm.setTruck(truck);
                    orderForm.setTruckId(truck.getObjectId());
                    orderForm.setRemark(strRemark);
                    orderForm.setStatus(OrderForm.PENDING);
                    Intent intent = new Intent(DeliverGoodsActivity.this, DeliverGoodsSendActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderForm", orderForm);
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
            case R.id.setGoodsInfo:
                Intent intentToGoodsInfo = new Intent(DeliverGoodsActivity.this, DeliverGoodsInfoActivity.class);
                intentToGoodsInfo.putExtra("handle", "set");
                startActivityForResult(intentToGoodsInfo, 3);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    route = (Route) bundle.getSerializable("route");
                    strShipper = route.getShipper();
                    strStarting = route.getStarting();
                    strReceiver = route.getReceiver();
                    strDestination = route.getDestination();
                    String strRoute = strShipper + ":" + strStarting + "————" + strReceiver + ":" + strDestination;
                    setRoute.setText(strRoute);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    truck = (Truck) bundle.getSerializable("truck");
                    setTruck.setText(truck.getType());
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    strWeight = data.getStringExtra("weight");
                    typeOfGoods = data.getStringExtra("type_of_goods");
                    setGoodsInfo.setText(strWeight + " " + typeOfGoods);
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        strRemark = charSequence.toString();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
