package com.a1stopclick.mylibrary.music;

//import androidx.activity.OnBackPressedCallback;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.base.fragmentback.BackFragment;
import com.a1stopclick.dependencyinjection.components.DaggerMusicLibraryComponent;
import com.a1stopclick.dependencyinjection.components.MusicLibraryComponent;
import com.a1stopclick.dependencyinjection.modules.MusicLibraryModule;
import com.a1stopclick.home.musiclist.AlbumListRecyclerViewAdapter;
import com.a1stopclick.mylibrary.music.MusicLibraryContract.View;
import com.domain.base.result.AlbumResult;
import com.domain.product.ProductResult;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 12/06/2019.
 * Music library fragment
 */

public class FragmentMusicLibrary extends BaseFragment implements BackFragment, View {

    private MusicLibraryComponent component;

    @Inject
    MusicLibraryContract.Presenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipe swipeRefreshLayout;

    @BindView(R.id.noItem)
    TextView noItem;

    @BindView(R.id.search_music)
    SearchView searchMusic;

    public static final int QUERY_SUBMITTED = 1;

    private String searchTrackQuery;

    AlbumListRecyclerViewAdapter recyclerViewAdapter;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void init() {
        initComponent();

        getBaseActivity().getSupportActionBar().setTitle("My Music");
        prepareRefreshLayout();
        presenter.getBuyedMusic();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerMusicLibraryComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .musicLibraryModule(new MusicLibraryModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
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
                presenter.getBuyedMusic();
            }
        });

        recyclerViewAdapter = new AlbumListRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBuyedMusicSuccess(List<AlbumResult> buyedMovies) {
        if (buyedMovies.size() == 0) {
            recyclerView.setVisibility(android.view.View.GONE);
            noItem.setVisibility(android.view.View.VISIBLE);
        } else {
            recyclerViewAdapter.setItems(buyedMovies);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(android.view.View.VISIBLE);
            noItem.setVisibility(android.view.View.GONE);
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

    public boolean onBackPressed() {
        // return true if you want to consume back-pressed event
        if (doubleBackToExitPressedOnce) {
            requireActivity().finishAffinity();
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(requireActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
        return true;
    }

    public int getBackPriority() {
        // use apropriate priority here
        return NORMAL_BACK_PRIORITY;
    }
}
