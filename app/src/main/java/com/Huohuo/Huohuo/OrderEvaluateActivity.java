package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.ActivityOrderEvaluateBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

/**
 * Created by Tony on 2017/3/11.
 */

public class OrderEvaluateActivity extends BaseActivity <ActivityOrderEvaluateBinding> implements CheckBox.OnCheckedChangeListener, RatingBar.OnRatingBarChangeListener, View.OnClickListener {

    private OrderForm orderForm;
    private Driver driver;

    private CardView driverCard;
    private EditText content;
    private RatingBar ratingBar;
    private CheckBox checkBox;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_order_evaluate);
        showContentView();
        initView();
        setTitle("评价");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, OrderEvaluateActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orderForm = (OrderForm) bundle.getSerializable("orderForm");
        driverCard = bindingView.driverCard.driverCard;
        driverCard.setOnClickListener(this);
        content = bindingView.content;
        ratingBar = bindingView.ratingBar;
        ratingBar.setOnRatingBarChangeListener(this);
        checkBox = bindingView.CheckBox;
        checkBox.setOnCheckedChangeListener(this);
        confirm = bindingView.confirm;
        confirm.setOnClickListener(this);
        driver = orderForm.getDriver();
        bindingView.driverCard.realName.setText(driver.getRealName());
        bindingView.driverCard.ratingBar.setRating(Float.parseFloat("" + driver.getRating()));
        bindingView.driverCard.rating.setText("" + driver.getRating());
        bindingView.driverCard.message.setText("已完成" + driver.getTaskCount() + "单");
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                final float rating = (ratingBar.getRating() + driver.getRating() * driver.getTaskCount()) / (driver.getTaskCount() + 1);
                final String evaluateToDriver = content.getText().toString();
                AVQuery queryDriver = new AVQuery("Driver");
                queryDriver.getInBackground(driver.getObjectId(), new GetCallback() {
                    @Override
                    public void done(AVObject avObject, AVException e) {
                        if (e == null) {
                            avObject.put("rating", rating);
                            avObject.saveInBackground();
                        }
                    }
                });
                AVQuery queryOrder = new AVQuery("OrderForm");
                queryOrder.getInBackground(orderForm.getObjectId(), new GetCallback() {
                    @Override
                    public void done(AVObject avObject, AVException e) {
                        if (e == null) {
                            avObject.put("evaluateToDriver", evaluateToDriver);
                            avObject.saveInBackground();
                            orderForm.setEvaluateToDriver(evaluateToDriver);
                            orderForm.save();
                        }
                    }
                });
                Toast.makeText(OrderEvaluateActivity.this, "评价完成", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.driver_card:
                Intent intent = new Intent(OrderEvaluateActivity.this, DriverInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("driver", driver);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

}
