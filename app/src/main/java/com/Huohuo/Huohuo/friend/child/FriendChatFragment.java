package com.Huohuo.Huohuo.friend.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.FriendChatAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.FriendChat;
import com.Huohuo.Huohuo.databinding.FragmentFriendChatBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendChatFragment extends BaseFragment<FragmentFriendChatBinding> {

    private List<FriendChat> friendChatList = new ArrayList<>();

    @Override
    public int setContent() {
        return R.layout.fragment_friend_chat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initFriendChat();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = bindingView.recycleView;
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        FriendChatAdapter adapter=new FriendChatAdapter(friendChatList);
        recyclerView.setAdapter(adapter);
    }

    private void initFriendChat() {
        for(int i=0;i<2;i++){
            FriendChat chat1 = new FriendChat("Logo团队","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            friendChatList.add(chat1);
            FriendChat chat2 = new FriendChat("张师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            friendChatList.add(chat2);
            FriendChat chat3 = new FriendChat("王师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            friendChatList.add(chat3);
            FriendChat chat4 = new FriendChat("刘师傅","亲爱的XXX,你好。欢迎使用XXX产品。","5天前");
            friendChatList.add(chat4);
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