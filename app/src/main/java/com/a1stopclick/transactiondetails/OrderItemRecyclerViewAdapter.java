package com.a1stopclick.transactiondetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.albumdetails.TrackItemViewHolder;
import com.domain.base.entity.OrderItem;
import com.domain.base.result.AlbumResult;
import com.domain.track.SongResult;

import java.util.List;

/**
 * Created by dendy.prtha
 */

public class OrderItemRecyclerViewAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {

    List<OrderItem> items;
    Context context;

    public OrderItemRecyclerViewAdapter(Context context, List<OrderItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_transaction_order_item, parent, false);
        return new OrderItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
