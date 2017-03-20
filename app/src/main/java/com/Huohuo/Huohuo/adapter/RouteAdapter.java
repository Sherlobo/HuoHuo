package com.Huohuo.Huohuo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.Route;

import java.util.List;

/**
 * Created by yqhok on 2017-02-26.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private List<Route> mRouteList;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Route route);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView starting;
        private TextView shipper;
        private TextView destination;
        private TextView receiver;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            starting = (TextView) view.findViewById(R.id.starting);
            shipper = (TextView) view.findViewById(R.id.shipper);
            destination = (TextView) view.findViewById(R.id.destination);
            receiver = (TextView) view.findViewById(R.id.receiver);
        }
    }

    public RouteAdapter(List<Route> routeList) {
        mRouteList = routeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_route, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = mRouteList.get(position);
        holder.starting.setText(route.getStarting());
        holder.shipper.setText(route.getShipper());
        holder.destination.setText(route.getDestination());
        holder.receiver.setText(route.getReceiver());
        holder.itemView.setTag(route);
    }

    @Override
    public int getItemCount() {
        return mRouteList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (Route)view.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
