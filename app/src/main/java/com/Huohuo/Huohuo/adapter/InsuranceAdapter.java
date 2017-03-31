package com.Huohuo.Huohuo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Huohuo.Huohuo.R;
import com.Huohuo.Huohuo.bean.Insurance;

import java.util.List;

/**
 * Created by JinBo on 2017/3/19.
 */

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.ViewHolder> {

    private Context mContext;
    private List<Insurance> InsuranceList;

    public InsuranceAdapter(List<Insurance> list){
        this.InsuranceList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_insurance,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Insurance insurance=InsuranceList.get(position);
        holder.kind.setText(insurance.getKind());
        holder.cost.setText(insurance.getCost());
    }

    @Override
    public int getItemCount() {
        return InsuranceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kind;
        private TextView cost;
        public ViewHolder(View view){
            super(view);
            kind=(TextView)view.findViewById(R.id.kind);
            cost=(TextView)view.findViewById(R.id.cost);
        }

    }
}
