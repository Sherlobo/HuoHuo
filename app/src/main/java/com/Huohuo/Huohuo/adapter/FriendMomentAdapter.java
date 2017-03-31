package com.Huohuo.Huohuo.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.FriendMoment;

import java.util.List;

/**
 * Created by Tony on 2017/3/5.
 */

public class FriendMomentAdapter extends RecyclerView.Adapter <FriendMomentAdapter.ViewHolder> {

    private List<FriendMoment> friendMomentList;

    public FriendMomentAdapter(List<FriendMoment> friendMomentList){
        this.friendMomentList = friendMomentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_moment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendMoment friendMoment = friendMomentList.get(position);
        holder.name.setText(friendMoment.getName());
        holder.time.setText(friendMoment.getTime());
        holder.evaluate.setText(friendMoment.getEvaluate());
        holder.ratingBar.setRating(friendMoment.getRating());
        holder.rating.setText("" + friendMoment.getRating());
    }

    @Override
    public int getItemCount() {
        return friendMomentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView time;
        private TextView evaluate;
        private RatingBar ratingBar;
        private TextView rating;

        public ViewHolder(View View) {
            super(View);
            name = (TextView) View.findViewById(R.id.name);
            time = (TextView) View.findViewById(R.id.time);
            evaluate = (TextView)View.findViewById(R.id.evaluate);
            ratingBar = (RatingBar) View.findViewById(R.id.rating_bar);
            rating = (TextView) View.findViewById(R.id.rating);

        }
    }

}
