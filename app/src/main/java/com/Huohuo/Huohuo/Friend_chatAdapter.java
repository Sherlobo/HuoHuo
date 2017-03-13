package com.Huohuo.Huohuo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tony on 2017/3/6.
 */

public class Friend_chatAdapter extends RecyclerView.Adapter <Friend_chatAdapter.ViewHolder>{
    private List<Friend_chat> mFriend_chatList;
    public Friend_chatAdapter(List<Friend_chat> friend_chatList){
        this.mFriend_chatList=friend_chatList;
    }

    @Override
    public Friend_chatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_chat,parent,false);
        Friend_chatAdapter.ViewHolder holder=new Friend_chatAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Friend_chatAdapter.ViewHolder holder, int position) {
        Friend_chat mFriend_chat=mFriend_chatList.get(position);
        holder.name.setText(mFriend_chat.getName());
        holder.time.setText(mFriend_chat.getTime());
        holder.chat.setText(mFriend_chat.getChat());
    }

    @Override
    public int getItemCount() {
        return mFriend_chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;
        private TextView chat;
        public ViewHolder(View view) {
            super(view);
            name=(TextView) view.findViewById(R.id.name);
            time=(TextView) view.findViewById(R.id.time);
            chat=(TextView)view.findViewById(R.id.chat);
        }
    }
}
