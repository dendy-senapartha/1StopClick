package com.a1stopclick.homeactivity.musiclist.albumdetails;

import android.content.Context;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class AlbumDetailPresenter implements AlbumDetailContract.Presenter {

    private final String TAG = AlbumDetailPresenter.class.getSimpleName();

    private final Context context;
    private final AlbumDetailContract.View view;

    @Inject
    public AlbumDetailPresenter(Context context, AlbumDetailContract.View view) {
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
