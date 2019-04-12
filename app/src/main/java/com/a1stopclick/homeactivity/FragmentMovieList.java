package com.a1stopclick.homeactivity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.view.View;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.vlcplayer.VlcOptionsProvider;
import com.vlcplayer.activities.MediaPlayerActivity;
//import com.a1stopclick.viewmovie.ViewMovieActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*
 * Created by dendy-prtha on 02/04/2019.
 * Fragment for Movie List
 */

public class FragmentMovieList extends BaseFragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static FragmentMovieList newInstance() {

        final FragmentMovieList mf = new FragmentMovieList();
        mf.setFragmentTitle("Movie");
        return mf;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected void init() {
        configureVlcPlayer();
        configureRecyclerView();
    }

    private void configureVlcPlayer() {
        VlcOptionsProvider.getInstance().setOptions((new VlcOptionsProvider.Builder(getBaseActivity())
                .setVerbose(true)
                .withSubtitleEncoding("KOI8-R").build()));
    }

    private void configureRecyclerView() {
        //dummy list
        String[] items = getResources().getStringArray(R.array.movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerViewAdapter(items));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent = new Intent(getContext(), ViewMovieActivity.class);
                //startActivity(intent);
                String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
                startMediaPlayerActivity(Uri.parse(vidAddress), null);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private final void startMediaPlayerActivity(Uri videoUri, Uri subtitleUri) {
        Intent intent = new Intent(getBaseActivity(), MediaPlayerActivity.class);

        intent.putExtra(MediaPlayerActivity.Companion.getMediaUri(), videoUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleUri(), subtitleUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleDestinationUri(), Uri.fromFile(getBaseActivity().getCacheDir()));
        intent.putExtra(MediaPlayerActivity.Companion.getOpenSubtitlesUserAgent(), "TemporaryUserAgent");
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleLanguageCode(), "rus");
        this.startActivity(intent);
    }
}
