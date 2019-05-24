package com.a1stopclick.homeactivity.movielist.moviedetails;

import android.content.Context;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private final String TAG = MovieDetailPresenter.class.getSimpleName();

    private final Context context;
    private final MovieDetailContract.View view;

    @Inject
    public MovieDetailPresenter(Context context, MovieDetailContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onDestroy() {

    }
}
