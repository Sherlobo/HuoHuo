package com.Huohuo.Huohuo.friend.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Friend_moment;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.Friend_momentAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentFriendMomentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendMomentFragment extends BaseFragment<FragmentFriendMomentBinding> {
    private List<Friend_moment> friend_momentList =new ArrayList<>();
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
        RecyclerView recyclerView=bindingView.recycleView;
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Friend_momentAdapter adapter=new Friend_momentAdapter(friend_momentList);
        recyclerView.setAdapter(adapter);
    }


    private void initFriendMoment() {
        for(int i=0;i<2;i++){
            Friend_moment moment1=new Friend_moment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.5f);
            friend_momentList.add(moment1);
            Friend_moment moment2=new Friend_moment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.4f);
            friend_momentList.add(moment2);
            Friend_moment moment3=new Friend_moment("王总评价了张师傅（浙BXXX）","2017年3月14日 21:00","昨天下的单子，没想到今天就到了，东西完好无损，师傅辛苦了。",4.3f);
            friend_momentList.add(moment3);


        }
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
