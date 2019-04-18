package com.a1stopclick.homeactivity.movielist;


import android.content.Intent;
import android.net.Uri;

import android.view.View;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.dependencyinjection.components.DaggerMovieListComponent;
import com.a1stopclick.dependencyinjection.components.MovieListComponent;
import com.a1stopclick.dependencyinjection.modules.MovieListModule;
import com.a1stopclick.homeactivity.RecyclerItemClickListener;
import com.a1stopclick.homeactivity.RecyclerViewAdapter;
import com.domain.movie.MovieListResult;
import com.vlcplayer.VlcOptionsProvider;
import com.vlcplayer.activities.MediaPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*
 * Created by dendy-prtha on 02/04/2019.
 * Fragment for Movie List
 */

public class FragmentMovieList extends BaseFragment implements MovieListContract.View{

    private MovieListComponent component;

    @Inject
    MovieListContract.Presenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected void init() {
        initComponent();
        configureVlcPlayer();
        //configureRecyclerView();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerMovieListComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .movieListModule(new MovieListModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

    private void configureVlcPlayer() {
        VlcOptionsProvider.getInstance().setOptions((new VlcOptionsProvider.Builder(getBaseActivity())
                .setVerbose(true)
                .withSubtitleEncoding("KOI8-R").build()));
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

    @Override
    public void onMovieListSuccess(List<MovieListResult> movieListResults) {
        //String[] items = getResources().getStringArray(R.array.movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerViewAdapter(movieListResults));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent = new Intent(getContext(), ViewMovieActivity.class);
                //startActivity(intent);
                //String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
                //String vidAddress = "https://ia802605.us.archive.org/7/items/CC_1916_10_02_ThePawnshop/CC_1916_10_02_ThePawnshop_512kb.mp4";

                startMediaPlayerActivity(Uri.parse(movieListResults.get(position).urldownload), null);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
