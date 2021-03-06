package com.sigma.prouds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.holder.NotifHolder;
import com.sigma.prouds.model.NotificationModel;

import java.util.List;

/**
 * Created by goy on 7/10/2017.
 */

public class NotifAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NotificationModel> list;

    public NotifAdapter(Context context, List<NotificationModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_notif, parent, false);
        return new NotifHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        NotifHolder item = (NotifHolder) holder;
        item.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
