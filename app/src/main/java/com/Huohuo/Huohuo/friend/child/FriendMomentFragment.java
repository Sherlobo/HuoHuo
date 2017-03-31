package com.Huohuo.Huohuo.friend.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.Friend_momentAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.FriendMoment;
import com.Huohuo.Huohuo.databinding.FragmentFriendMomentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendMomentFragment extends BaseFragment<FragmentFriendMomentBinding> {

    private List<FriendMoment> friendMomentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_friend_moment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initFriendMoment();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Friend_momentAdapter adapter = new Friend_momentAdapter(friendMomentList);
        recyclerView.setAdapter(adapter);
    }


    private void initFriendMoment() {
            FriendMoment moment1 = new FriendMoment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.5f);
            friendMomentList.add(moment1);
            FriendMoment moment2 = new FriendMoment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.4f);
            friendMomentList.add(moment2);
            FriendMoment moment3 = new FriendMoment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.3f);
            friendMomentList.add(moment3);
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.recycleView.setFocusable(false);
    }

    @Override
    public void onPause() {
        super.onPause();
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
