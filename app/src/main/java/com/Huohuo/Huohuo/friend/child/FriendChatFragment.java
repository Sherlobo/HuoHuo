package com.Huohuo.Huohuo.friend.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.Friend_chat;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.Friend_chatAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.databinding.FragmentFriendChatBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendChatFragment extends BaseFragment<FragmentFriendChatBinding> {

    private List<Friend_chat> mFriend_chatList = new ArrayList<>();

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
        RecyclerView recyclerView=bindingView.recycleView;
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Friend_chatAdapter adapter=new Friend_chatAdapter(mFriend_chatList);
        recyclerView.setAdapter(adapter);
    }

    private void initFriendChat() {
            Friend_chat chat1=new Friend_chat("Logo团队","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat1);
            Friend_chat chat2=new Friend_chat("张师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat2);
            Friend_chat chat3=new Friend_chat("王师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat3);
            Friend_chat chat4=new Friend_chat("刘师傅","亲爱的XXX,你好。欢迎使用XXX产品。","5天前");
            mFriend_chatList.add(chat4);
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