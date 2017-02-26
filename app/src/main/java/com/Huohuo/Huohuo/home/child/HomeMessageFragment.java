package com.Huohuo.Huohuo.home.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentHomeMessageBinding;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by yqhok on 2017-02-22.
 */

public class HomeMessageFragment extends BaseFragment<FragmentHomeMessageBinding> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initView();
        initRecycleView();
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_message;
    }

    private void initView() {
        Banner banner = bindingView.banner;
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load("").into(imageView);
            }
        });
    }

    private void initRecycleView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.recycleView.setFocusable(false);
    }

    @Override
    protected void onRefresh() {
        showContentView();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}