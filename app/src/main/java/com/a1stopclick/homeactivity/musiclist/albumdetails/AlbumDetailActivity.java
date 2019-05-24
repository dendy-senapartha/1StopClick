package com.a1stopclick.homeactivity.musiclist.albumdetails;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.AlbumDetailsComponent;
import com.a1stopclick.dependencyinjection.components.DaggerAlbumDetailsComponent;
import com.a1stopclick.dependencyinjection.components.MovieDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.AlbumDetailsModule;

import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 10/05/2019.
 * album detail activity
 */

public class AlbumDetailActivity extends BaseActivity implements AlbumDetailContract.View {

    public static final String ALBUM_ITEM = "album_item";

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

    @BindView(R.id.img_backdrop)
    ImageView img_backdrop;

    @BindView(R.id.tracks_recycler_view)
    RecyclerView trRecyclerView;

    AlbumResult albumDetails = null;

    @Inject
    AlbumDetailContract.Presenter presenter;

    private AlbumDetailsComponent component;

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
        String albumItem = getIntent().getStringExtra(ALBUM_ITEM);
        loadData(albumItem);
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

    private void loadData(String albumItem) {
        albumDetails = JSON.parseObject(albumItem, AlbumResult.class);

        getSupportActionBar().setTitle(albumDetails.album.name);
        tv_title.setText(albumDetails.album.name);

        Glide.with(AlbumDetailActivity.this)
                .load(albumDetails.album.albumImageUrl)
                .into(img_backdrop);

        tvReleaseDate.setText(AndroidUtils.getLongDate(albumDetails.album.releaseDate.toString()));
        String genres = null;
        for (int trackCount = 0; trackCount < albumDetails.album.tracks.size(); trackCount++) {
            genres = albumDetails.album.tracks.get(trackCount).product.subcategory.name;
        }
        tvGenres.setText(genres);

        trRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trRecyclerView.setAdapter(new TrackListRecyclerViewAdapter(albumDetails.album.tracks, this));
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
}
