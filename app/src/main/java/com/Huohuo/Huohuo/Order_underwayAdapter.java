package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tony on 2017/3/1.
 */

public class Order_underwayAdapter extends RecyclerView.Adapter<Order_underwayAdapter.ViewHolder> {
    private Context mContext;
    private List<Order_underway> mOrder_underwayList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView starting;
        private TextView destination;
        private TextView cost;
        private TextView status;

        public ViewHolder(View View) {
            super(View);
            time=(TextView) View.findViewById(R.id.time);
            starting=(TextView) View.findViewById(R.id.starting);
            destination=(TextView) View.findViewById(R.id.destination);
            cost=(TextView) View.findViewById(R.id.cost);
            status=(TextView) View.findViewById(R.id.status);
        }
    }

    public Order_underwayAdapter(List<Order_underway> Order_underwayList){
        mOrder_underwayList=Order_underwayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_order_underway,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Order_underway order_underway=mOrder_underwayList.get(position);
        holder.time.setText(order_underway.getTime());
        holder.starting.setText(order_underway.getStarting());
        holder.destination.setText(order_underway.getDestination());
        holder.cost.setText(order_underway.getCost());
        holder.status.setText(order_underway.getStatus());

    }

    @Override
    public int getItemCount() {
        return mOrder_underwayList.size();
    }
}
