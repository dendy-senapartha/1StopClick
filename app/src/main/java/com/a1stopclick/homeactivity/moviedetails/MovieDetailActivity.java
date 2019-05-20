package com.a1stopclick.homeactivity.moviedetails;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerMovieDetailsComponent;
import com.a1stopclick.dependencyinjection.components.MovieDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.MovieDetailsModule;
import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.base.result.ProductResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.vlcplayer.activities.MediaPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie detail activity
 */

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    public static final String MOVIE_ITEM = "movie_item";

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.img_backdrop)
    ImageView img_backdrop;

    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.tv_release_date)
    TextView tv_release_date;

    @BindView(R.id.tv_vote)
    TextView tv_vote;

    @BindViews({
            R.id.img_star1,
            R.id.img_star2,
            R.id.img_star3,
            R.id.img_star4,
            R.id.img_star5
    })
    List<ImageView> img_vote;

    @BindView(R.id.tv_genres)
    TextView tv_genres;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.tv_trailer_unavailable)
    TextView tvTrailerUnavailable;

    ProductResult movieDetails = null;

    @Inject
    MovieDetailContract.Presenter presenter;

    private MovieDetailsComponent component;

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void init() {
        initComponent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        String movie_item = getIntent().getStringExtra(MOVIE_ITEM);
        loadData(movie_item);
    }

    private void loadData(String movie_item) {
        movieDetails = JSON.parseObject(movie_item, ProductResult.class);

        getSupportActionBar().setTitle(movieDetails.product.productName);
        tv_title.setText(movieDetails.product.productName);
        for (int i = 0; i < movieDetails.product.productImageList.size(); i++) {
            String imageType = movieDetails.product.productImageList.get(i).productImageType.code;
            if (imageType.equalsIgnoreCase("MovieArt")) {
                Glide.with(MovieDetailActivity.this)
                        .load(movieDetails.product.productImageList.get(i).imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_detail_product)
                        .into(img_poster);
            }
            if (imageType.equalsIgnoreCase("MovieBackdrop")) {
                Glide.with(MovieDetailActivity.this)
                        .load(movieDetails.product.productImageList.get(i).imageUrl)
                        .into(img_backdrop);
            }
        }

        tv_release_date.setText(AndroidUtils.getLongDate(movieDetails.product.created.toString()));
        //tv_vote.setText(String.valueOf(movieDetails.getVoteAverage()));
        tv_genres.setText(movieDetails.product.subcategory.name);
        tv_overview.setText(movieDetails.product.description);

        //double userRating = movieDetails.getVoteAverage() / 2;
        //int integerPart = (int) userRating;

        // Fill stars
        //for (int i = 0; i < integerPart; i++) {
        //    img_vote.get(i).setImageResource(R.drawable.ic_star_black_24dp);
        //}

        // Fill half star
        //if (Math.round(userRating) > integerPart) {
        //     img_vote.get(integerPart).setImageResource(R.drawable.ic_star_half_black_24dp);
        //}
        for (int i = 0; i < movieDetails.product.videoList.size(); i++) {
            String imageType = movieDetails.product.videoList.get(i).videoType.code;
            if (imageType.equalsIgnoreCase("trailer")) {
                String movieUrl = movieDetails.product.videoList.get(i).streamUrl;
                tvTrailerUnavailable.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.VISIBLE);
                getLifecycle().addObserver(youTubePlayerView);

                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        if (movieUrl != null) {
                            YouTubePlayerUtils.loadOrCueVideo(
                                    youTubePlayer,
                                    getLifecycle(),
                                    movieUrl,
                                    0f
                            );
                        }
                    }
                });
            } else {
                tvTrailerUnavailable.setVisibility(View.VISIBLE);
                youTubePlayerView.setVisibility(View.GONE);
            }
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

    @OnClick(R.id.watchMovie)
    public void onClickWatchMovie(View view) {
        for (int i = 0; i < movieDetails.product.videoList.size(); i++) {
            String imageType = movieDetails.product.videoList.get(i).videoType.code;
            if (imageType.equalsIgnoreCase("movie")) {
                String movieUrl = movieDetails.product.videoList.get(i).streamUrl;
                startMediaPlayerActivity(movieDetails.product.productName, Uri.parse(movieUrl), null);
            }
        }
    }

    private final void startMediaPlayerActivity(String videoTitle, Uri videoUri, Uri subtitleUri) {
        Intent intent = new Intent(this, MediaPlayerActivity.class);

        intent.putExtra(MediaPlayerActivity.Companion.getMediaTitle(), videoTitle);
        intent.putExtra(MediaPlayerActivity.Companion.getMediaUri(), videoUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleUri(), subtitleUri);
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleDestinationUri(), Uri.fromFile(this.getCacheDir()));
        intent.putExtra(MediaPlayerActivity.Companion.getOpenSubtitlesUserAgent(), "TemporaryUserAgent");
        intent.putExtra(MediaPlayerActivity.Companion.getSubtitleLanguageCode(), "eng");
        this.startActivity(intent);
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerMovieDetailsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .movieDetailsModule(new MovieDetailsModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
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
