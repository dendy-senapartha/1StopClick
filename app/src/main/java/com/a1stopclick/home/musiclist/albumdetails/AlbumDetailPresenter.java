package com.a1stopclick.home.musiclist.albumdetails;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.account.AccountResult;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class AlbumDetailPresenter implements AlbumDetailContract.Presenter {

    private final String TAG = AlbumDetailPresenter.class.getSimpleName();

    private final Context context;
    private final AlbumDetailContract.View view;
    private AccountResult userSession = null;

    @Inject
    public AlbumDetailPresenter(Context context, AlbumDetailContract.View view) {
        this.context = context;
        this.view = view;

    }

    @Override
    public void initPresenter() {
        userSession = ((OneStopClickApplication) context).getSession();
    }


    @Override
    public void onDestroy() {

    }
}
