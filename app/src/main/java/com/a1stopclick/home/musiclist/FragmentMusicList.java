package com.a1stopclick.home.musiclist;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerMusicListComponent;
import com.a1stopclick.dependencyinjection.components.MusicListComponent;
import com.a1stopclick.dependencyinjection.modules.MusicListModule;
import com.domain.base.entity.Album;
import com.domain.base.result.AlbumResult;
import com.domain.product.ProductResult;

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
 * Created by dendy-prtha on 26/04/2019.
 * UI fragment for music list
 */

public class FragmentMusicList extends BaseFragment implements MusicListContract.View {

    private final String TAG = FragmentMusicList.class.getSimpleName();

    private MusicListComponent component;

    @Inject
    MusicListContract.Presenter presenter;

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

    private Handler searchTrackQueryHandler = new SearchTrackQueryHandler(this);
    AlbumListRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void init() {
        initComponent();
        prepareRefreshLayout();
        configureSearchTextField();
        presenter.getMusicList();
    }

    private void configureSearchTextField() {
        searchMusic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals(searchTrackQuery)) { // avoid a consecutive api request
                    presenter.findTrackByTitle(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTrackQuery = newText; // store the query

                searchTrackQueryHandler.removeMessages(QUERY_SUBMITTED);
                searchTrackQueryHandler.sendEmptyMessageDelayed(QUERY_SUBMITTED, 1000);
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
                presenter.getMusicList();
            }
        });


        recyclerViewAdapter = new AlbumListRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    static class SearchTrackQueryHandler extends Handler {
        private final WeakReference<FragmentMusicList> mFragment;

        SearchTrackQueryHandler(FragmentMusicList mFragment) {
            this.mFragment = new WeakReference<>(mFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentMusicList fragment = mFragment.get();
            if (fragment != null) {
                if (msg.what == QUERY_SUBMITTED) {
                    fragment.presenter.findTrackByTitle(fragment.searchTrackQuery);
                }
            }
        }
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerMusicListComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .musicListModule(new MusicListModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

    @Override
    public void onAlbumListSuccess(List<AlbumResult> albumResultList) {
        if (albumResultList.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noItem.setVisibility(View.VISIBLE);
        } else {
            recyclerViewAdapter.setItems(albumResultList);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFindTrackSuccess(List<ProductResult> productResults) {
        if (productResults.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noItem.setVisibility(View.VISIBLE);
            recyclerViewAdapter.setItems(new ArrayList<>());
            recyclerViewAdapter.notifyDataSetChanged();
        } else {
            List<AlbumResult> albumResultList = new ArrayList<>();

            //todo : need to simplify this one
            /*
            for (int productCount = 0; productCount < productResults.size(); productCount++) {
                for (int trackCount = 0; trackCount < productResults.get(productCount).product.trackList.size(); trackCount++) {
                    productResults.get(productCount).product.trackList.get(trackCount).product = productResults.get(productCount).product;
                    for (int albumCount = 0; albumCount < productResults.get(productCount).product.trackList.get(trackCount).albums.size();
                         albumCount++) {
                        Album album = productResults.get(productCount).product.trackList.get(trackCount).albums.get(albumCount);
                        album.tracks = productResults.get(productCount).product.trackList;
                        AlbumResult albumResult = new AlbumResult();
                        albumResult.album = album;
                        if (albumResultList.size() == 0) {
                            albumResultList.add(albumResult);
                        } else {
                            if(!isAlbumInList(albumResultList, albumResult.album))
                            {
                                albumResultList.add(albumResult);
                            }
                        }
                    }
                }
            }*/

            recyclerViewAdapter.setItems(albumResultList);
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
        }
    }

    private boolean isAlbumInList(List<AlbumResult> albumResultList , Album album)
    {
        for (int albumResultCount = 0; albumResultCount < albumResultList.size(); albumResultCount++) {
            if (albumResultList.get(albumResultCount).album.name.equalsIgnoreCase(album.name)) {
                return true;
            }
        }
        return false;
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
