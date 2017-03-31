package com.Huohuo.Huohuo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.FriendChat;

import java.util.List;

/**
 * Created by Tony on 2017/3/6.
 */

public class FriendChatAdapter extends RecyclerView.Adapter <FriendChatAdapter.ViewHolder>{

    private List<FriendChat> friendChatList;

    public FriendChatAdapter(List<FriendChat> friendChatList){
        this.friendChatList = friendChatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_chat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendChat friendChat = friendChatList.get(position);
        holder.name.setText(friendChat.getName());
        holder.time.setText(friendChat.getTime());
        holder.chat.setText(friendChat.getChat());
    }

    @Override
    public int getItemCount() {
        return friendChatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView time;
        private TextView chat;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            chat = (TextView)view.findViewById(R.id.chat);
        }
    }
}
