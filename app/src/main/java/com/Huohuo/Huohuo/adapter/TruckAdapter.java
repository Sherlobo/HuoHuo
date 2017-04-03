package com.Huohuo.Huohuo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.Truck;

import java.util.List;


/**
 * Created by JinBo on 2017/3/21.
 */

public class TruckAdapter extends RecyclerView.Adapter <TruckAdapter.ViewHolder> implements View.OnClickListener{
    private Context mContext;

    private List<Truck> truckList;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public TruckAdapter(List<Truck> truckList){
        this.truckList = truckList;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Truck truck);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_truck, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Truck truck = truckList.get(position);
        holder.type.setText(truck.getType());
        holder.weight.setText(truck.getWeight());
        holder.inicost.setText("" + truck.getInicost());
        holder.overcost.setText("" + truck.getOvercost());
        holder.size.setText(truck.getSize());
        holder.truck_picture.setImageResource(truck.getImageId());
        holder.itemView.setTag(truck);

    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (Truck)view.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView weight;
        private TextView inicost;
        private TextView size;
        private TextView overcost;
        private ImageView truck_picture;
        public ViewHolder(View view) {
            super(view);
            type = (TextView)view.findViewById(R.id.type);
            weight = (TextView)view.findViewById(R.id.weight);
            inicost = (TextView)view.findViewById(R.id.inicost);
            size = (TextView)view.findViewById(R.id.size);
            overcost = (TextView)view.findViewById(R.id.overcost);
            truck_picture = (ImageView)view.findViewById(R.id.truck_picture);
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
