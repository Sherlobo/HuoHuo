package com.Huohuo.Huohuo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.Huohuo.Huohuo.base.BaseActivity;
import com.Huohuo.Huohuo.bean.Client;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.ActivityPersonalInfoBinding;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yqhok on 2017-02-25.
 */

public class PersonalInfoActivity extends BaseActivity<ActivityPersonalInfoBinding> implements View.OnClickListener {

    private CircleImageView headShot;
    private TextView realName;
    private TextView briefIntroduce;
    private TextView orderCount;

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        showContentView();
        initClient();
        initView();
    }

    private void initClient() {
        client = new Client();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String id = preferences.getString("id", "");
        final List<OrderForm> list = DataSupport.findAll(OrderForm.class);
        if (!id.isEmpty()) {
            AVQuery<AVObject> avQuery = new AVQuery<>("Client");
            avQuery.getInBackground(id, new GetCallback<AVObject>() {
                @Override
                public void done(AVObject avObject, AVException e) {
                    if (e == null) {
                        client.setPhone(avObject.get("phone").toString());
                        client.setRealName(avObject.get("realName").toString());
                        client.setOrderCount(Integer.parseInt(avObject.get("orderCount").toString()));
                        client.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                        client.setOrderCount(0);
                        for (OrderForm orderForm : list) {
                            if (orderForm.getStatus() == OrderForm.FINISHED) {
                                client.setOrderCount(client.getOrderCount() + 1);
                            }
                        }
                        avObject.put("orderCount", client.getOrderCount());
                        avObject.saveInBackground();
                    } else {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void initView() {
        headShot = bindingView.headShot;
        realName = bindingView.realName;
        briefIntroduce = bindingView.briefIntro;
        orderCount = bindingView.orderCount;
        realName.setText(client.getRealName());
        orderCount.setText("" + client.getOrderCount());
        briefIntroduce.setText(client.getBriefIntroduce());
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonalInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_person_info, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit:
                PersonalInfoEditActivity.start(PersonalInfoActivity.this);
                break;
            case R.id.share:
                break;
        }
    }
}
