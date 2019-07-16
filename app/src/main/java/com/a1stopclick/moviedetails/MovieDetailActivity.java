package com.a1stopclick.moviedetails;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerMovieDetailsComponent;
import com.a1stopclick.dependencyinjection.components.MovieDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.MovieDetailsModule;
import com.a1stopclick.home.movielist.FragmentMovieList;
import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.domain.order.AddItemToOrderResult;
import com.domain.product.ProductResult;
import com.domain.video.VideoResult;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
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

    @BindView(R.id.btnWatchMovie)
    MaterialButton btnBuyOrWatchMovie;

    @BindView(R.id.movieStatusLoadingBar)
    ProgressBar movieStatusLoadingBar;

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.tv_trailer_unavailable)
    TextView tvTrailerUnavailable;

    ProductResult product = null;

    String videoTrailerUrl = "";

    String videoMovieUrl = "";

    boolean movieNotBuyed;

    @Inject
    MovieDetailContract.Presenter presenter;

    private MovieDetailsComponent component;

    @Override
    public int getLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    public void init() {
        initComponent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        loadData();
    }

    private void loadData() {
        String movie_item = getIntent().getStringExtra(MOVIE_ITEM);
        product = JSON.parseObject(movie_item, ProductResult.class);

        getSupportActionBar().setTitle(product.product.productName);
        tv_title.setText(product.product.productName);
        for (int i = 0; i < product.product.productImageList.size(); i++) {
            String imageType = product.product.productImageList.get(i).productImageType.code;
            if (imageType.equalsIgnoreCase("MovieArt")) {
                Glide.with(MovieDetailActivity.this)
                        .load(product.product.productImageList.get(i).imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_detail_product)
                        .into(img_poster);
            }
            if (imageType.equalsIgnoreCase("MovieBackdrop")) {
                Glide.with(MovieDetailActivity.this)
                        .load(product.product.productImageList.get(i).imageUrl)
                        .into(img_backdrop);
            }
        }

        tv_release_date.setText(AndroidUtils.getLongDate(product.product.created.toString()));
        //tv_vote.setText(String.valueOf(product.getVoteAverage()));
        tv_genres.setText(product.product.subcategory.name);
        tv_overview.setText(product.product.description);

        presenter.checAlreadyBuyedkMovie(String.valueOf(product.product.id));

        //double userRating = product.getVoteAverage() / 2;
        //int integerPart = (int) userRating;

        // Fill stars
        //for (int i = 0; i < integerPart; i++) {
        //    img_vote.get(i).setImageResource(R.drawable.ic_star_black_24dp);
        //}

        // Fill half star
        //if (Math.round(userRating) > integerPart) {
        //     img_vote.get(integerPart).setImageResource(R.drawable.ic_star_half_black_24dp);
        //}
        presenter.findVideoByProductId(String.valueOf(product.product.id));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnWatchMovie)
    public void onClickWatchMovie(View view) {
        if (movieNotBuyed) {
            presenter.addMovieToOrder(product.product.id, 1);
        } else {
            if (!videoMovieUrl.isEmpty()) {
                startMediaPlayerActivity(product.product.productName, Uri.parse(videoMovieUrl), null);
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

    @Override
    public void onGetVideoByProductIdSuccess(List<VideoResult> videoResults) {
        extractVideoUrl(videoResults);
        //prepare youtube trailer video
        if (!videoTrailerUrl.isEmpty()) {
            tvTrailerUnavailable.setVisibility(View.GONE);
            youTubePlayerView.setVisibility(View.VISIBLE);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    YouTubePlayerUtils.loadOrCueVideo(
                            youTubePlayer,
                            getLifecycle(),
                            videoTrailerUrl,
                            0f
                    );
                }
            });
        } else {
            tvTrailerUnavailable.setVisibility(View.VISIBLE);
            youTubePlayerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccessFindVideoByProductId(List<ProductResult> result) {
        if (result.isEmpty()) {
            movieNotBuyed = true;
            btnBuyOrWatchMovie.setText("Buy Movie");
        } else {
            movieNotBuyed = false;
            btnBuyOrWatchMovie.setText("Watch Movie");
        }
        movieStatusLoadingBar.setVisibility(View.GONE);
        btnBuyOrWatchMovie.setVisibility(View.VISIBLE);
    }

    private void extractVideoUrl(List<VideoResult> videoResults) {
        for (int i = 0; i < videoResults.size(); i++) {
            String videoTypeCode = videoResults.get(i).video.videoType.code;
            if (videoTypeCode.equalsIgnoreCase("trailer")) {
                videoTrailerUrl = videoResults.get(i).video.streamUrl;
            } else if (videoTypeCode.equalsIgnoreCase("movie")) {
                videoMovieUrl = videoResults.get(i).video.streamUrl;
            }
        }
    }

    @Override
    public ProductResult getMovieDetail() {
        return product;
    }

    @Override
    public void onAddMovieToOrderSuccess(AddItemToOrderResult result) {
        setResult(FragmentMovieList.REQUEST_REFRESH_MOVIE_LIST, new Intent());
        finish();
    }
}
