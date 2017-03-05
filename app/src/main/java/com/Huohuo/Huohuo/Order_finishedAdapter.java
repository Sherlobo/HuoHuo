package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tony on 2017/3/4.
 */

public class Order_finishedAdapter extends RecyclerView.Adapter<Order_finishedAdapter.ViewHolder> {
    private Context mContext;
    private List<Order_finished>  mOrder_finishedList;

    static public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView starting;
        private TextView destination;
        private TextView cost;
        private TextView othercost;
        private TextView allcost;
        private TextView status;
        public ViewHolder(View View) {
            super(View);
            time=(TextView) View.findViewById(R.id.time);
            starting=(TextView) View.findViewById(R.id.starting);
            destination=(TextView) View.findViewById(R.id.destination);
            cost=(TextView) View.findViewById(R.id.cost);
            othercost=(TextView)View.findViewById(R.id.othercost);
            allcost=(TextView)View.findViewById(R.id.allcost);
            status=(TextView) View.findViewById(R.id.status);
        }
    }

    public Order_finishedAdapter(List<Order_finished> Order_finishedList){
        mOrder_finishedList=Order_finishedList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_order_finished,parent,false);
        Order_finishedAdapter.ViewHolder holder=new Order_finishedAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order_finished order_finished=mOrder_finishedList.get(position);
        holder.time.setText(order_finished.getTime());
        holder.starting.setText(order_finished.getStarting());
        holder.destination.setText(order_finished.getDestination());
        holder.cost.setText(order_finished.getCost());
        holder.othercost.setText(order_finished.getOthercost());
        holder.allcost.setText(order_finished.getAllcost());
        holder.status.setText(order_finished.getStatus());

    }

    @Override
    public int getItemCount() {
        return mOrder_finishedList.size();
    }


}
