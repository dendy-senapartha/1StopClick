package com.a1stopclick.mylibrary.movie;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerMovieLibraryComponent;
import com.a1stopclick.dependencyinjection.components.MovieLibraryComponent;
import com.a1stopclick.dependencyinjection.modules.MovieLibraryModule;
import com.a1stopclick.home.movielist.MovieRecyclerViewAdapter;
import com.domain.product.ProductResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 12/06/2019.
 * Movie Library activity
 */

public class FragmentMovieLibrary extends BaseFragment implements MovieLibraryContract.View {

    private MovieLibraryComponent component;

    @Inject
    MovieLibraryContract.Presenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipe swipeRefreshLayout;

    @BindView(R.id.noMovieText)
    TextView noMovieText;

    @BindView(R.id.search_movie)
    SearchView searchMovie;

    //Todo : maybe need to replace with dedicated adapter?
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;

    public static final int QUERY_SUBMITTED = 1;

    private String searchMoviedQuery;

    @Override
    public int getLayout() {
        return R.layout.fragment_movies_list;
    }

    @Override
    public void init() {
        initComponent();
        prepareRefreshLayout();
        configureSearchTextField();
        presenter.getBuyedMovie();
        getBaseActivity().getSupportActionBar().setTitle("My Movies");
    }

    private Handler searchMovieQueryHandler = new SearchMovieQueryHandler(this);

    @Override
    public void onGetBuyedMoviesSuccess(List<ProductResult> buyedMovies) {
        if (buyedMovies.size() == 0) {
            noMovieText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            movieRecyclerViewAdapter.setItems(buyedMovies);
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
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(active));
    }

    static class SearchMovieQueryHandler extends Handler {
        private final WeakReference<FragmentMovieLibrary> mFragment;

        SearchMovieQueryHandler(FragmentMovieLibrary mFragment) {
            this.mFragment = new WeakReference<>(mFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentMovieLibrary fragment = mFragment.get();
            if (fragment != null) {
                if (msg.what == QUERY_SUBMITTED) {
                    fragment.presenter.findBuyedMovieByTitle(fragment.searchMoviedQuery);
                }
            }
        }
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getBuyedMovie();
        });
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void configureSearchTextField() {
        //searchMovie.setIconifiedByDefault(false);
        searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals(searchMoviedQuery)) { // avoid a consecutive api request
                    //presenter.findMovieByTitle(query);
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

    private void initComponent() {
        if (component == null) {
            component = DaggerMovieLibraryComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .movieLibraryModule(new MovieLibraryModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

}
