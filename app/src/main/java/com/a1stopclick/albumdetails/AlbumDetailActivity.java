package com.a1stopclick.albumdetails;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.AlbumDetailsComponent;
import com.a1stopclick.dependencyinjection.components.DaggerAlbumDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.AlbumDetailsModule;

import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.base.result.AlbumResult;
import com.domain.order.AddItemToOrderResult;
import com.domain.track.SongResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;

/*
 * Created by dendy-prtha on 10/05/2019.
 * album detail activity
 */

public class AlbumDetailActivity extends BaseActivity implements AlbumDetailContract.View {

    public static final String ALBUM_ITEM = "album_item";
    public static final String USER_ID_FROM_CALLER = "user_id_from_caller";

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;

    @BindView(R.id.tv_vote)
    TextView tvVote;

    @BindViews({
            R.id.img_star1,
            R.id.img_star2,
            R.id.img_star3,
            R.id.img_star4,
            R.id.img_star5
    })
    List<ImageView> imgVote;

    @BindView(R.id.tv_genres)
    TextView tvGenres;

    @BindView(R.id.allSongAlreadyOwned)
    TextView allSongAlreadyOwned;

    @BindView(R.id.img_backdrop)
    ImageView img_backdrop;

    @BindView(R.id.tracks_recycler_view)
    RecyclerView trRecyclerView;

    AlbumResult albumDetails = null;

    @Inject
    AlbumDetailContract.Presenter presenter;

    private AlbumDetailsComponent component;

    private List<SongResult> songResultList;

    @Override
    public int getLayout() {
        return R.layout.activity_album_detail;
    }

    @Override
    public void init() {
        initComponent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        trRecyclerView.setAdapter(null);
        loadData();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerAlbumDetailsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .albumDetailsModule(new AlbumDetailsModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

    private void loadData() {
        albumDetails = JSON.parseObject(getIntent().getStringExtra(ALBUM_ITEM), AlbumResult.class);
        String userIdFromCaller = getIntent().getStringExtra(USER_ID_FROM_CALLER);

        getSupportActionBar().setTitle(albumDetails.album.name);
        tv_title.setText(albumDetails.album.name);

        Glide.with(AlbumDetailActivity.this)
                .load(albumDetails.album.albumImageUrl)
                .into(img_backdrop);

        tvReleaseDate.setText(AndroidUtils.getLongDate(albumDetails.album.releaseDate.toString()));

        if (userIdFromCaller == null) {
            presenter.getAlbumSongs("" + albumDetails.album.id);
        } else {
            presenter.getUserBuyedAlbumSongs("" + userIdFromCaller, "" + albumDetails.album.id);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void onGetAlbumSongsSuccess(List<SongResult> result) {
        songResultList = result;
        if (!result.isEmpty()) {
            trRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            trRecyclerView.setItemAnimator(new DefaultItemAnimator());
            trRecyclerView.setAdapter(new TrackListRecyclerViewAdapter(this, result, albumDetails,
                    TrackListRecyclerViewAdapter.ALBUM_SONGS));
        } else {
            trRecyclerView.setVisibility(View.GONE);
            allSongAlreadyOwned.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setAlbumDetailGenre(List<SongResult> result) {
        if (!result.isEmpty()) {
            tvGenres.setText(result.get(0).song.subcategory.name);
        }
    }

    @Override
    public void onGetUserBuyedAlbumSongs(List<SongResult> result) {
        songResultList = result;
        trRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trRecyclerView.setAdapter(new TrackListRecyclerViewAdapter(this, result, albumDetails,
                TrackListRecyclerViewAdapter.USER_SONGS));

    }

    @Override
    public void onAddSongToOrderSuccess(AddItemToOrderResult result) {
        if (Boolean.parseBoolean(result.status)) {
            //remove the song from the album list

            for (Iterator<SongResult> songOfTheAlbumIterator = songResultList.iterator();
                 songOfTheAlbumIterator.hasNext(); ) {
                SongResult songOfTheAlbum = songOfTheAlbumIterator.next();
                if (result.itemId.equalsIgnoreCase(songOfTheAlbum.song.id + "")) {
                    songOfTheAlbumIterator.remove();
                }
            }
            onGetAlbumSongsSuccess(songResultList);
        }
    }

    public AlbumDetailContract.Presenter getPresenter() {
        return presenter;
    }
}
