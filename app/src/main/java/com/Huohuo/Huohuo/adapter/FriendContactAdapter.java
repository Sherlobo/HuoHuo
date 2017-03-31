package com.Huohuo.Huohuo.adapter;

import android.support.v7.widget.RecyclerView;

import com.Huohuo.Huohuo.bean.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yqhok on 2017-03-29.
 */

public abstract class FriendContactAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<Person> personList = new ArrayList<>();

    public FriendContactAdapter() {
        setHasStableIds(true);
    }

    public void addAll(Collection<? extends Person> collection) {
        if (collection != null) {
            personList.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public Person getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

}
