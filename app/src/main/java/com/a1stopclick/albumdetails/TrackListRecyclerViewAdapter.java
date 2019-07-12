package com.a1stopclick.albumdetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.Track;
import com.domain.base.result.AlbumResult;
import com.domain.track.SongResult;

import java.util.List;

/**
 * Created by dendy.prtha
 */

public class TrackListRecyclerViewAdapter extends RecyclerView.Adapter<TrackItemViewHolder> {

    public static final String ALBUM_SONGS = "ALBUM_SONGS";
    public static final String USER_SONGS = "USER_SONGS";

    List<SongResult> items;
    Context context;
    AlbumResult albumDetails;
    String typeOfTrackList;

    public TrackListRecyclerViewAdapter(Context context, List<SongResult> items, AlbumResult albumDetails, String typeOfTrackList) {
        this.items = items;
        this.context = context;
        this.albumDetails = albumDetails;
        this.typeOfTrackList = typeOfTrackList;
    }

    @Override
    public TrackItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_track_item, parent, false);
        return new TrackItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(TrackItemViewHolder holder, int position) {
        holder.bind(items.get(position), albumDetails);
        if (typeOfTrackList.equalsIgnoreCase(ALBUM_SONGS)) {
            holder.setButtonTitle(TrackItemViewHolder.buyButtonTitle);
        } else if (typeOfTrackList.equalsIgnoreCase(USER_SONGS)) {
            holder.setButtonTitle(TrackItemViewHolder.playButtonTitle);
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
