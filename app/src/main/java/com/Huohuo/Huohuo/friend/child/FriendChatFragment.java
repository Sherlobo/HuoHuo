package com.Huohuo.Huohuo.friend.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Huohuo.Huohuo.MsgActivity;
import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.adapter.Friend_chatAdapter;
import com.Huohuo.Huohuo.base.BaseFragment;
import com.Huohuo.Huohuo.bean.FriendChat;
import com.Huohuo.Huohuo.databinding.FragmentFriendChatBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqhok on 2017-02-23.
 */

public class FriendChatFragment extends BaseFragment<FragmentFriendChatBinding> {

    private List<FriendChat> mFriend_chatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Friend_chatAdapter adapter;

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
        recyclerView=bindingView.recycleView;
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Friend_chatAdapter(mFriend_chatList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Friend_chatAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view,FriendChat friend_chat) {
                Intent intent = new Intent(getActivity(), MsgActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFriendChat() {
            FriendChat chat1=new FriendChat("Logo团队","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat1);
            FriendChat chat2=new FriendChat("张师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat2);
            FriendChat chat3=new FriendChat("王师傅","亲爱的XXX,你好。欢迎使用XXX产品。","16.20");
            mFriend_chatList.add(chat3);
            FriendChat chat4=new FriendChat("刘师傅","亲爱的XXX,你好。欢迎使用XXX产品。","5天前");
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