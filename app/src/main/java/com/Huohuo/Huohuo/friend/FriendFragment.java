package com.Huohuo.Huohuo.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.Huohuo.Huohuo.MyFragmentPagerAdapter;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentFriendBinding;
import com.Huohuo.Huohuo.friend.child.FriendChatFragment;
import com.Huohuo.Huohuo.friend.child.FriendContactFragment;
import com.Huohuo.Huohuo.friend.child.FriendMomentFragment;

import java.util.ArrayList;

/**
 * Created by yqhok on 2017-02-22.
 */

public class FriendFragment extends BaseFragment<FragmentFriendBinding> {

    private ArrayList<String> titleList = new ArrayList<>(3);
    private ArrayList<Fragment> fragmentsList = new ArrayList<>(3);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initFragment();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList, titleList);
        bindingView.vpFriend.setAdapter(myAdapter);
        bindingView.vpFriend.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabFriend.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabFriend.setupWithViewPager(bindingView.vpFriend);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_friend;
    }

    private void initFragment() {
        titleList.add("私信");
        titleList.add("好友");
        titleList.add("动态");
        fragmentsList.add(new FriendChatFragment());
        fragmentsList.add(new FriendContactFragment());
        fragmentsList.add(new FriendMomentFragment());
    }

}
