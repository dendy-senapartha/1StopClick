package com.a1stopclick.homeactivity.musiclist;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerMusicListComponent;
import com.a1stopclick.dependencyinjection.components.MusicListComponent;
import com.a1stopclick.dependencyinjection.modules.MusicListModule;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;
import com.vlcplayer.activities.MediaPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

    @BindView(R.id.musicListContainer)
    LinearLayout musicListContainer;

    @BindView(R.id.noItemContainer)
    LinearLayout noItemContainer;


    @Override
    protected int getLayout() {
        return R.layout.fragment_music_list;
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
                presenter.getMusicList();
            }
        });
        recyclerView.setAdapter(null);
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
    public void onMusicListSuccess(List<AlbumResult> albumResultList) {
        if (albumResultList.size() == 0) {
            musicListContainer.setVisibility(View.GONE);
            noItemContainer.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new AlbumListRecyclerViewAdapter(albumResultList, this));
            musicListContainer.setVisibility(View.VISIBLE);
            noItemContainer.setVisibility(View.GONE);
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
