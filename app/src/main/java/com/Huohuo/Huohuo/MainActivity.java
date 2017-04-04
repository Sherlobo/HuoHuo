package com.Huohuo.Huohuo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.Huohuo.Huohuo.adapter.MyFragmentPagerAdapter;
import com.Huohuo.Huohuo.bean.Client;
import com.Huohuo.Huohuo.bean.Driver;
import com.Huohuo.Huohuo.bean.OrderForm;
import com.Huohuo.Huohuo.databinding.ActivityMainBinding;
import com.Huohuo.Huohuo.friend.FriendFragment;
import com.Huohuo.Huohuo.home.HomeFragment;
import com.Huohuo.Huohuo.order.OrderFragment;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private FrameLayout titleMenu;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager vpContent;
    private ImageView titleMenuImage;
    private CircleImageView headShot;
    private TextView realName;
    private TextView briefIntroduce;

    private ActivityMainBinding mBinding;
    private ImageView home;
    private ImageView order;
    private ImageView message;

    List<Fragment> fragmentList = new ArrayList<>();

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initDb();
        initView();
        initContentFragment();
        initDrawerLayout();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void initDb() {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String phone = preferences.getString("phone", "");
        AVQuery<AVObject> queryId = new AVQuery<>("Client");
        queryId.whereEqualTo("phone", phone);
        queryId.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    String id = avObject.getObjectId();
                    AVUser user = AVUser.getCurrentUser();
                    user.put("id", id);
                    user.saveInBackground();
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putString("id", id);
                    editor.apply();
                    AVQuery<AVObject> avQuery = new AVQuery<>("Client");
                    avQuery.getInBackground(id, new GetCallback<AVObject>() {
                        @Override
                        public void done(AVObject avObject, AVException e) {
                            if (e == null) {
                                DataSupport.deleteAll(Client.class);
                                client = new Client();
                                client.setPhone(avObject.get("phone").toString());
                                client.setRealName(avObject.get("realName").toString());
                                client.setOrderCount(Integer.parseInt(avObject.get("orderCount").toString()));
                                client.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                                client.save();
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
                    DataSupport.deleteAll(OrderForm.class);
                    DataSupport.deleteAll(Driver.class);
                    final AVQuery<AVObject> query = new AVQuery<>("OrderForm");
                    query.whereEqualTo("clientId", id);
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (e == null) {
                                for (AVObject object : list) {
                                    final OrderForm orderForm = new OrderForm();
                                    orderForm.setObjectId(object.getObjectId());
                                    orderForm.setStartTime(object.get("startTime").toString());
                                    orderForm.setShipper(object.get("shipper").toString());
                                    orderForm.setStarting(object.get("starting").toString());
                                    orderForm.setReceiver(object.get("receiver").toString());
                                    orderForm.setDestination(object.get("destination").toString());
                                    orderForm.setWeight(object.get("weight").toString());
                                    orderForm.setTypeOfGoods(object.get("typeOfGoods").toString());
                                    orderForm.setRemark(object.get("remark").toString());
                                    orderForm.setMile(Double.parseDouble(object.get("mile").toString()));
                                    orderForm.setPrice(Double.parseDouble(object.get("price").toString()));
                                    orderForm.setStatus(Integer.parseInt(object.get("status").toString()));
                                    orderForm.save();
                                    if (object.get("driverId") != null) {
                                        orderForm.setDriverId(object.get("driverId").toString());
                                        initDriver(orderForm);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void initDriver(final OrderForm orderForm) {
        AVQuery<AVObject> query = new AVQuery<>("Driver");
        query.getInBackground(orderForm.getDriverId(), new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    Driver driver = new Driver();
                    driver.setObjectId(avObject.getObjectId());
                    driver.setPhone(avObject.get("phone").toString());
                    driver.setRealName(avObject.get("realName").toString());
                    driver.setIdNumber(avObject.get("idNumber").toString());
                    driver.setTaskCount(Integer.parseInt(avObject.get("taskCount").toString()));
                    driver.setRating(Float.parseFloat(avObject.get("rating").toString()));
                    driver.setBriefIntroduce(avObject.get("briefIntroduce").toString());
                    driver.save();
                }
            }
        });
    }

    private void initContentFragment() {
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new FriendFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        vpContent.setAdapter(adapter);
        vpContent.setOffscreenPageLimit(3);
        vpContent.addOnPageChangeListener(this);
        mBinding.include.home.setSelected(true);
        vpContent.setCurrentItem(0);
    }

    private void initView() {
        titleMenu = mBinding.include.titleMenu;
        toolbar = mBinding.include.toolbarHome;
        drawerLayout = mBinding.drawerLayout;
        navigationView = mBinding.navView;
        floatingActionButton = mBinding.include.fab;
        vpContent = mBinding.include.vpContent;

        home = mBinding.include.home;
        order = mBinding.include.order;
        message = mBinding.include.message;
        titleMenuImage = mBinding.include.titleMenuImage;

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initDrawerLayout() {
        navigationView.inflateHeaderView(R.layout.nav_header_main);
        navigationView.inflateMenu(R.menu.activity_main_drawer);
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(this);
        headShot = (CircleImageView) headerView.findViewById(R.id.head_shot);
        headShot.setOnClickListener(this);
        navigationView.setOnClickListener(this);
        ImageView qrcode = (ImageView) headerView.findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                mBinding.drawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NavQrcodeActivity.start(MainActivity.this);
                    }
                },360);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.wallet:
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                        mBinding.drawerLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NavWalletActivity.start(MainActivity.this);
                            }
                        },360);
                        break;
                    case R.id.settings:
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                        mBinding.drawerLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NavSettingsActivity.start(MainActivity.this);
                            }
                        },360);
                        break;
                    case R.id.supportCenter:
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                        mBinding.drawerLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NavSupportCenterActivity.start(MainActivity.this);
                            }
                        },360);
                        break;
                    case R.id.aboutUs:
                        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
                        mBinding.drawerLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NavAboutUsActivity.start(MainActivity.this);
                            }
                        },360);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initListener() {
        titleMenu.setOnClickListener(this);
        mBinding.include.home.setOnClickListener(this);
        mBinding.include.order.setOnClickListener(this);
        mBinding.include.message.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        titleMenuImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_menu_image:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.home:
                if (vpContent.getCurrentItem() != 0) {
                    home.setSelected(true);
                    order.setSelected(false);
                    message.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.order:
                if (vpContent.getCurrentItem() != 1) {
                    order.setSelected(true);
                    home.setSelected(false);
                    message.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            case R.id.message:
                if (vpContent.getCurrentItem() != 2) {
                    message.setSelected(true);
                    home.setSelected(false);
                    order.setSelected(false);
                    vpContent.setCurrentItem(2);
                }
                break;
            case R.id.fab:
                DeliverGoodsActivity.start(MainActivity.this);
                break;
            case R.id.head_shot:
                PersonalInfoActivity.start(MainActivity.this);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setQueryHint("输入要查找的内容");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                home.setSelected(true);
                order.setSelected(false);
                message.setSelected(false);
                break;
            case 1:
                order.setSelected(true);
                home.setSelected(false);
                message.setSelected(false);
                break;
            case 2:
                message.setSelected(true);
                home.setSelected(false);
                order.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
