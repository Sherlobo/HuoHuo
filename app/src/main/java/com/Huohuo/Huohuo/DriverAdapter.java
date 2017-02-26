package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    private Context mContext;

    private List<Driver> mDriverList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView headShot;
        private TextView name;
        private RatingBar ratingBar;
        private TextView rating;
        private TextView message;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            headShot = (ImageView) view.findViewById(R.id.head_shot);
            name = (TextView) view.findViewById(R.id.name);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            rating = (TextView) view.findViewById(R.id.rating);
            message = (TextView) view.findViewById(R.id.message);
        }
    }

    public DriverAdapter(List<Driver> driverList) {
        mDriverList = driverList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_driver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Driver driver = mDriverList.get(position);
        holder.headShot.setImageResource(driver.getImageId());
        holder.name.setText(driver.getName());
        holder.ratingBar.setRating(driver.getRating());
        holder.rating.setText("" + driver.getRating());
        holder.message.setText("已完成" + driver.getTaskCount() + "单");
    }

    @Override
    public int getItemCount() {
        return mDriverList.size();
    }
}
