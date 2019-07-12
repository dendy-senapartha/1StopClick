package com.a1stopclick.albumdetails;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.Track;
import com.domain.base.result.AlbumResult;
import com.domain.track.SongResult;
import com.google.android.material.button.MaterialButton;
import com.vlcplayer.activities.MediaPlayerActivity;

public class TrackItemViewHolder extends RecyclerView.ViewHolder {
    public static final String buyButtonTitle = "Buy";
    public static final String playButtonTitle = "Play";
    private TextView trackTitle;
    private MaterialButton button;
    Context context;

    public TrackItemViewHolder(View itemView, Context context) {
        super(itemView);
        trackTitle = itemView.findViewById(R.id.trackTitle);
        button = itemView.findViewById(R.id.buyOrPlayTrack);
        this.context = context;
    }

    public void setTrackTitle(String text) {
        trackTitle.setText(text);
    }

    public void setButtonTitle(String text) {
        button.setText(text);
    }

    public void bind(SongResult item, AlbumResult albumDetails) {
        setTrackTitle(item.song.productName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageUrl = null;
                imageUrl = albumDetails.album.albumImageUrl;

                if (button.getText().toString().equalsIgnoreCase(playButtonTitle)) {
                    for (Track track : item.song.trackList) {
                        String trackType = track.trackType.code;
                        if (trackType.equalsIgnoreCase("music")) {
                            startAudioPlayerActivity(item.song.productName,
                                    Uri.parse(track.streamUrl), Uri.parse(imageUrl));
                        }
                    }
                } else if (button.getText().toString().equalsIgnoreCase(buyButtonTitle)) {
                    if (context instanceof AlbumDetailActivity) {
                        ((AlbumDetailActivity) context).getPresenter().addSongToOrder(item.song.id, 1);
                    }
                }

            }
        });
    }

    private final void startAudioPlayerActivity(String videoTitle, Uri videoUri, Uri mediaImageUri) {
        Intent intent = new Intent(context, MediaPlayerActivity.class);

        intent.putExtra(MediaPlayerActivity.Companion.getMediaTitle(), videoTitle);
        intent.putExtra(MediaPlayerActivity.Companion.getMediaUri(), videoUri);
        intent.putExtra(MediaPlayerActivity.Companion.getMediaImageUri(), mediaImageUri);
        context.startActivity(intent);
    }

}
