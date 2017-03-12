package com.Huohuo.Huohuo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yqhok on 2017-03-05.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context mContext;
    private List<Order> orderList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView starting;
        private TextView destination;
        private TextView cost;
        private TextView status;
        private LinearLayout llExtend;
        private Button confirm;
        private Button extend;

        public ViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            starting = (TextView) view.findViewById(R.id.starting);
            destination = (TextView) view.findViewById(R.id.destination);
            cost = (TextView) view.findViewById(R.id.cost);
            status = (TextView) view.findViewById(R.id.status);
            llExtend = (LinearLayout) view.findViewById(R.id.ll_extend);
            confirm = (Button) view.findViewById(R.id.confirm);
            extend = (Button) view.findViewById(R.id.extend);
        }
    }

    public OrderAdapter(List<Order> OrderList){
        this.orderList = OrderList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_order,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.time.setText(order.getTime());
        holder.starting.setText(order.getStarting());
        holder.destination.setText(order.getDestination());
        holder.cost.setText(order.getCost());
        switch (order.getStatus()) {
            case Order.UNDERWAY:
                holder.llExtend.setVisibility(View.GONE);
                break;
            case Order.WAITING:
                holder.confirm.setText("确认收货");
                holder.extend.setText("延长收货");
                break;
            case Order.FINISHED:
                holder.confirm.setText("评价");
                holder.extend.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
