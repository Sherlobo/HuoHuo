package com.Huohuo.Huohuo.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yqhok.project_2.R;

import java.util.ArrayList;
import java.util.List;

public class Register extends FragmentActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private LinearLayout mBasicInformation;
    private LinearLayout mCertification;

    private ImageButton mBasicInformationImg;
    private ImageButton mCertificationImg;

    private Button mCross;
    Fragment tab001;
    Fragment tab002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }
    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.container);
        mBasicInformation = (LinearLayout)findViewById(R.id.BasicInformation);
        mCertification = (LinearLayout)findViewById(R.id.Certification);
        mBasicInformationImg = (ImageButton)findViewById(R.id.BasicInformation_btn);
        mCertificationImg = (ImageButton)findViewById(R.id.Certification_btn);
        mCross = (Button)findViewById(R.id.Cross);

        tab001 = new register1Fragment();
        tab002 = new register2Fragment();

        mFragments.add(tab001);
        mFragments.add(tab002);

        mAdapter = new FragmentPagerAdapter( getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        viewPager.setAdapter(mAdapter);
    }
    private void initEvent() {
        // 设置事件
        mBasicInformation.setOnClickListener(this);
        mCertification.setOnClickListener(this);
        mCross.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                //当前选中的Fragment 下标
                int currentItem = viewPager.getCurrentItem();
                //把图片全设置为暗的
                resetImg();
                switch (currentItem) {
                    case 0:
                        mBasicInformationImg.setImageResource(R.drawable.login_usr_in);
                        break;
                    case 1:
                        mCertificationImg.setImageResource(R.drawable.login_code_in);
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.BasicInformation:
                setSelect(0);
                break;
            case R.id.Certification:
                setSelect(1);
                break;
            case R.id.Cross:
                finish();
                break;
            default:
                break;
        }
    }
    private void setSelect(int i){
        //改变内容区域，把图片设置为亮的
        switch (i) {
            case 0:
                mBasicInformationImg.setImageResource(R.drawable.login_usr_in);
                break;
            case 1:
                mCertificationImg.setImageResource(R.drawable.login_code_in);
                break;
            default:
                break;
        }
        //切换Fragment
        viewPager.setCurrentItem(i);
    }
    //将所有的图片都变暗
    private void resetImg(){
        mBasicInformationImg.setImageResource(R.drawable.login_usr_in);
        mCertificationImg.setImageResource(R.drawable.login_code_in);
    }
}


