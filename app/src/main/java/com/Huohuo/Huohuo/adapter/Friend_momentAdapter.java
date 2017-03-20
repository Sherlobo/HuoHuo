package com.Huohuo.Huohuo.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.Huohuo.Huohuo.Friend_moment;
import com.Huohuo.Huohuo.R;

import java.util.List;

/**
 * Created by Tony on 2017/3/5.
 */

public class Friend_momentAdapter extends RecyclerView.Adapter <Friend_momentAdapter.ViewHolder> {
    private List<Friend_moment> mFriend_momentList;

    public Friend_momentAdapter(List<Friend_moment> Friend_moment){
        mFriend_momentList=Friend_moment;
    }
    @Override
    public Friend_momentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_moment,parent,false);
        Friend_momentAdapter.ViewHolder holder=new Friend_momentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Friend_momentAdapter.ViewHolder holder, int position) {
        Friend_moment friend_moment=mFriend_momentList.get(position);
        holder.name.setText(friend_moment.getName());
        holder.time.setText(friend_moment.getTime());
        holder.evaluate.setText(friend_moment.getEvaluate());
        holder.ratingBar.setRating(friend_moment.getRating());
        holder.rating.setText("" + friend_moment.getRating());
    }

    @Override
    public int getItemCount() {
        return mFriend_momentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView time;
        private TextView evaluate;
        private RatingBar ratingBar;
        private TextView rating;

        public ViewHolder(View View) {
            super(View);
            name=(TextView) View.findViewById(R.id.name);
            time=(TextView) View.findViewById(R.id.time);
            evaluate=(TextView)View.findViewById(R.id.evaluate);
            ratingBar = (RatingBar) View.findViewById(R.id.rating_bar);
            rating = (TextView) View.findViewById(R.id.rating);

        }
    }


}
