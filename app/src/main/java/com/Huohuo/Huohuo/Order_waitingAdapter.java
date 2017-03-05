package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Huohuo.Huohuo.order.child.OrderWaitingFragment;

import java.util.List;


/**
 * Created by Tony on 2017/3/3.
 */

public class Order_waitingAdapter extends RecyclerView.Adapter <Order_waitingAdapter.ViewHolder>{

    private Context mContext;
    private List<Order_waiting> mOrder_waitingList;

      static class ViewHolder extends RecyclerView.ViewHolder {
            View Order_waitingView;
            private TextView time;
            private TextView starting;
            private TextView destination;
            private TextView cost;
            private TextView othercost;
            private TextView allcost;
            private TextView status;
            private Button extend;

            public ViewHolder(View View) {
                super(View);
                Order_waitingView =View;
                time=(TextView) View.findViewById(R.id.time);
                starting=(TextView) View.findViewById(R.id.starting);
                destination=(TextView) View.findViewById(R.id.destination);
                cost=(TextView) View.findViewById(R.id.cost);
                othercost=(TextView)View.findViewById(R.id.othercost);
                allcost=(TextView)View.findViewById(R.id.allcost);
                status=(TextView) View.findViewById(R.id.status);
                extend=(Button)View.findViewById(R.id.extend);
            }
    }

    public Order_waitingAdapter(List<Order_waiting> Order_waitingList){
        mOrder_waitingList=Order_waitingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        final View view= LayoutInflater.from(mContext).inflate(R.layout.item_order_waiting,parent,false);
        final Order_waitingAdapter.ViewHolder holder=new Order_waitingAdapter.ViewHolder(view);
        holder.extend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int position=holder.getAdapterPosition();
                Order_waiting order_waiting=mOrder_waitingList.get(position);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order_waiting order_waiting=mOrder_waitingList.get(position);
        holder.time.setText(order_waiting.getTime());
        holder.starting.setText(order_waiting.getStarting());
        holder.destination.setText(order_waiting.getDestination());
        holder.cost.setText(order_waiting.getCost());
        holder.othercost.setText(order_waiting.getOthercost());
        holder.allcost.setText(order_waiting.getAllcost());
        holder.status.setText(order_waiting.getStatus());
    }

    @Override
    public int getItemCount() {
        return mOrder_waitingList.size();
    }
}
