package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    private Context mContext;

    private List<Driver> mDriverList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
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
    }

    @Override
    public int getItemCount() {
        return mDriverList.size();
    }
}
