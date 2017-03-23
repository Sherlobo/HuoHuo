package com.Huohuo.Huohuo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.OrderForm;

import java.util.List;

/**
 * Created by yqhok on 2017-03-05.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private List<OrderForm> orderFormList;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, OrderForm orderForm);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView startTime;
        private TextView starting;
        private TextView destination;
        private TextView mileEst;
        private TextView priceEst;
        private TextView status;
        private LinearLayout llExtend;
        private Button confirm;
        private Button extend;

        public ViewHolder(View view) {
            super(view);
            startTime = (TextView) view.findViewById(R.id.start_time);
            starting = (TextView) view.findViewById(R.id.starting);
            destination = (TextView) view.findViewById(R.id.destination);
            mileEst = (TextView) view.findViewById(R.id.mileage_est);
            priceEst = (TextView) view.findViewById(R.id.price_est);
            status = (TextView) view.findViewById(R.id.status);
            llExtend = (LinearLayout) view.findViewById(R.id.ll_extend);
            confirm = (Button) view.findViewById(R.id.confirm);
            extend = (Button) view.findViewById(R.id.extend);
        }
    }

    public OrderAdapter(List<OrderForm> orderFormList){
        this.orderFormList = orderFormList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderForm orderForm = orderFormList.get(position);
        holder.startTime.setText("发货时间" + orderForm.getStartTime());
        holder.starting.setText(orderForm.getStarting());
        holder.destination.setText(orderForm.getDestination());
        if (orderForm.getStatus() == OrderForm.FINISHED) {
            holder.mileEst.setText("里程： " + orderForm.getMile() + "公里");
            holder.priceEst.setText("费用： " + orderForm.getPrice() + "元");
        } else {
            holder.mileEst.setText("预估里程： " + orderForm.getMile());
            holder.priceEst.setText("预估费用： " + orderForm.getPrice());
        }
        switch (orderForm.getStatus()) {
            case OrderForm.PENDING:
                holder.status.setText("待接单");
                holder.llExtend.setVisibility(View.GONE);
                break;
            case OrderForm.UNDERWAY:
                holder.status.setText("进行中");
                holder.llExtend.setVisibility(View.GONE);
                break;
            case OrderForm.WAITING:
                holder.status.setText("待收货");
                holder.confirm.setText("确认收货");
                holder.extend.setText("延长收货");
                break;
            case OrderForm.FINISHED:
                holder.status.setText("已完成");
                holder.confirm.setText("评价");
                holder.extend.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        holder.itemView.setTag(orderForm);
    }

    @Override
    public int getItemCount() {
        return orderFormList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (OrderForm) view.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
