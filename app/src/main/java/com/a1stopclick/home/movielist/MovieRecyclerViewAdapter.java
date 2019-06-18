package com.a1stopclick.home.movielist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1stopclick.R;
import com.domain.product.ProductResult;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dendy.prtha
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {

    List<ProductResult> items;
    Fragment parentFragment;

    public MovieRecyclerViewAdapter(List<ProductResult> items, Fragment fragment) {
        this.items = items;
        this.parentFragment = fragment;
    }

    public MovieRecyclerViewAdapter(List<ProductResult> items) {
        this.items = items;
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_movie_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        holder.bind(items.get(position));
        if (parentFragment != null) {
            for (int i = 0; i < items.get(position).product.productImageList.size(); i++) {
                String imageType = items.get(position).product.productImageList.get(i).productImageType.code;
                if (imageType.equalsIgnoreCase("MovieArt")) {
                    holder.setItemMoviePoster(items.get(position).product.productImageList.get(i).imageUrl, parentFragment);
                }
            }
        }
    }

    public void setItems( List<ProductResult> items)
    {
        this.items.clear();
        this.items = items;
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
