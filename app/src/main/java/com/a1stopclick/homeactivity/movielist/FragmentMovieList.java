package com.a1stopclick.homeactivity.movielist;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerMovieListComponent;
import com.a1stopclick.dependencyinjection.components.MovieListComponent;
import com.a1stopclick.dependencyinjection.modules.MovieListModule;
import com.domain.base.result.ProductResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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

    @BindView(R.id.noMovieText)
    TextView noMovieText;

    @BindView(R.id.search_movie)
    SearchView searchMovie;

    public static final int QUERY_SUBMITTED = 1;

    private String searchMoviedQuery;

    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected void init() {
        initComponent();
        prepareRefreshLayout();
        configureSearchTextField();
    }

    private Handler searchMovieQueryHandler = new SearchMovieQueryHandler(this);

    static class SearchMovieQueryHandler extends Handler {
        private final WeakReference<FragmentMovieList> mFragment;
        SearchMovieQueryHandler(FragmentMovieList mFragment) {
            this.mFragment = new WeakReference<>(mFragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentMovieList fragment = mFragment.get();
            if (fragment != null) {
                if (msg.what == QUERY_SUBMITTED) {
                    fragment.presenter.findMovieByTitle(fragment.searchMoviedQuery);
                }
            }
        }
    }

    private void configureSearchTextField() {
        //searchMovie.setIconifiedByDefault(false);
        searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals(searchMoviedQuery)) { // avoid a consecutive api request
                    presenter.findMovieByTitle(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMoviedQuery = newText; // store the query

                searchMovieQueryHandler.removeMessages(QUERY_SUBMITTED);
                searchMovieQueryHandler.sendEmptyMessageDelayed(QUERY_SUBMITTED, 1000);
                return false;
            }
        });
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
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerViewAdapter.notifyDataSetChanged();
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

    @Override
    public void onMovieListSuccess(List<ProductResult> movieListResults) {
        if (movieListResults.size() == 0) {
            noMovieText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            movieRecyclerViewAdapter.setItems(movieListResults);
            movieRecyclerViewAdapter.notifyDataSetChanged();
            noMovieText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
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
