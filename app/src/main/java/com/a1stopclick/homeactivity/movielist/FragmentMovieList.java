package com.a1stopclick.homeactivity.movielist;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerMovieListComponent;
import com.a1stopclick.dependencyinjection.components.MovieListComponent;
import com.a1stopclick.dependencyinjection.modules.MovieListModule;
import com.a1stopclick.homeactivity.RecyclerItemClickListener;
import com.a1stopclick.homeactivity.HomeActivityRecyclerViewAdapter;
import com.a1stopclick.homeactivity.moviedetails.MovieDetailActivity;
import com.a1stopclick.homeactivity.moviedetails.MovieDetailContract;
import com.domain.base.ProductResult;
import com.domain.movie.MovieListResult;
import com.vlcplayer.activities.MediaPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

/*
 * Created by dendy-prtha on 02/04/2019.
 * Fragment for Movie List
 */

public class FragmentMovieList extends BaseFragment implements MovieListContract.View {

    private final String TAG = FragmentMovieList.class.getSimpleName();

    private MovieListComponent component;

    @Inject
    MovieListContract.Presenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipe swipeRefreshLayout;

    @BindView(R.id.movieListContainer)
    LinearLayout movieListContainer;

    @BindView(R.id.noMovieContainer)
    LinearLayout noMovieContainer;

    @Override
    protected int getLayout() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected void init() {
        initComponent();
        prepareRefreshLayout();
    }

    private void prepareRefreshLayout() {
        // Set up progress indicator
        /**/
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMovieList();
            }
        });
        recyclerView.setAdapter(null);
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

    private final void startMediaPlayerActivity(String videoTitle, Uri videoUri, Uri subtitleUri) {
        Intent intent = new Intent(getBaseActivity(), MediaPlayerActivity.class);

        intent.putExtra(MediaPlayerActivity.Companion.getMediaTitle(), videoTitle);
        intent.putExtra(MediaPlayerActivity.Companion.getMediaUri(), videoUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleUri(), subtitleUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleDestinationUri(), Uri.fromFile(getBaseActivity().getCacheDir()));
        intent.putExtra(MediaPlayerActivity.Companion.getOpenSubtitlesUserAgent(), "TemporaryUserAgent");
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleLanguageCode(), "eng");
        this.startActivity(intent);
    }

    @Override
    public void onMovieListSuccess(List<ProductResult> movieListResults) {
        if (movieListResults.size() == 0) {
            noMovieContainer.setVisibility(View.VISIBLE);
            movieListContainer.setVisibility(View.GONE);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new HomeActivityRecyclerViewAdapter(movieListResults, this));

            noMovieContainer.setVisibility(View.GONE);
            movieListContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }

        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }
}
