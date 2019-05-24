package com.a1stopclick.homeactivity.musiclist.albumdetails;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.Track;
import com.google.android.material.button.MaterialButton;
import com.vlcplayer.activities.MediaPlayerActivity;

public class TrackItemViewHolder extends RecyclerView.ViewHolder {
    private TextView trackTitle;
    private MaterialButton button;
    Context context;


    public TrackItemViewHolder(View itemView, Context context) {
        super(itemView);
        trackTitle = (TextView) itemView.findViewById(R.id.trackTitle);
        button = (MaterialButton) itemView.findViewById(R.id.buyTrack);
        this.context = context;
    }

    public void setTrackTitle(String text) {
        trackTitle.setText(text);
    }

    public void bind(Track item) {
        setTrackTitle(item.product.productName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = null;
                for (int i = 0; i < item.product.productImageList.size(); i++) {
                    String imageType = item.product.productImageList.get(i).productImageType.code;
                    if (imageType.equalsIgnoreCase("MovieArt")) {
                        imageUrl = item.product.productImageList.get(i).imageUrl;
                    }
                }
                startAudioPlayerActivity(item.product.productName, Uri.parse(item.streamUrl), Uri.parse(imageUrl));
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
