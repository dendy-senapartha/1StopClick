package com.a1stopclick.homeactivity.musiclist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;

import java.util.List;

/**
 * Created by dendy.prtha
 */

public class AlbumListRecyclerViewAdapter extends RecyclerView.Adapter<AlbumItemViewHolder> {

    List<AlbumResult> items;
    Fragment parentFragment;

    public AlbumListRecyclerViewAdapter(List<AlbumResult> items, Fragment fragment) {
        this.items = items;
        this.parentFragment = fragment;
    }

    public AlbumListRecyclerViewAdapter(List<AlbumResult> items) {
        this.items = items;
    }

    @Override
    public AlbumItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_album_item, parent, false);
        return new AlbumItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumItemViewHolder holder, int position) {
        holder.bind(items.get(position));
        if (parentFragment != null) {
            holder.setMusicPoster(items.get(position).album.albumImageUrl, parentFragment);
            holder.setItemMovieTitle(items.get(position).album.name);
        }
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
