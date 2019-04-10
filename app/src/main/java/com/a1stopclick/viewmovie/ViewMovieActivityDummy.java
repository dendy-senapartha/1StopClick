package com.a1stopclick.viewmovie;



import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.widget.FrameLayout;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.vlcplayer.VlcOptionsProvider;
import com.vlcplayer.activities.MediaPlayerActivity;

import com.vlcplayer.VlcOptionsProvider.Builder;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 08/04/2019.
 * View Movie Activity
 */

public class ViewMovieActivityDummy extends BaseActivity {
    public static final String TAG = ViewMovieActivityDummy.class.getSimpleName();

    @BindView(R.id.framelayout_fragment_container)
    FrameLayout fragmentContainer;

    String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

    @Override
    public int getLayout() {
        return R.layout.activity_media_player;
    }

    @Override
    public void init() {
        VlcOptionsProvider.getInstance().setOptions((new Builder((Context)this)).setVerbose(true).withSubtitleEncoding("KOI8-R").build());

        startMediaPlayerActivity(Uri.parse(vidAddress),null);
    }

    private final void startMediaPlayerActivity(Uri videoUri, Uri subtitleUri) {
        Intent var3 = new Intent(this, MediaPlayerActivity.class);

        var3.putExtra(MediaPlayerActivity.Companion.getMediaUri(), videoUri);
        var3.putExtra(MediaPlayerActivity.Companion.getSubtitleUri(),  subtitleUri);
        var3.putExtra(MediaPlayerActivity.Companion.getSubtitleDestinationUri(), Uri.fromFile(this.getCacheDir()));
        var3.putExtra(MediaPlayerActivity.Companion.getOpenSubtitlesUserAgent(), "TemporaryUserAgent");
        var3.putExtra(MediaPlayerActivity.Companion.getSubtitleLanguageCode(), "rus");
        this.startActivity(var3);
    }

}
