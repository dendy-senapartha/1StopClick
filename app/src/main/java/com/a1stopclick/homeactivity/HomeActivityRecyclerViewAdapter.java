package com.a1stopclick.homeactivity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1stopclick.R;
import com.domain.base.ProductResult;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dendy.prtha
 */

public class HomeActivityRecyclerViewAdapter extends RecyclerView.Adapter<TextItemViewHolder> {

    List<ProductResult> items;

    public HomeActivityRecyclerViewAdapter(List<ProductResult> items) {
        this.items = items;
    }

    @Override
    public TextItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_item, parent, false);
        return new TextItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextItemViewHolder holder, int position) {
        holder.bind(items.get(position).productName);
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