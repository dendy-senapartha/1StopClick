package com.a1stopclick.homeactivity.musiclist.albumdetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.Track;

import java.util.List;

/**
 * Created by dendy.prtha
 */

public class TrackListRecyclerViewAdapter extends RecyclerView.Adapter<TrackItemViewHolder> {

    List<Track> items;
    Context context;

    public TrackListRecyclerViewAdapter(List<Track> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public TrackListRecyclerViewAdapter(List<Track> items) {
        this.items = items;
    }

    @Override
    public TrackItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_track_item, parent, false);
        return new TrackItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(TrackItemViewHolder holder, int position) {
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
