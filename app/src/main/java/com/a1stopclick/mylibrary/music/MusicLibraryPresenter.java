package com.a1stopclick.mylibrary.music;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.album.interactor.GetUserBuyedAlbum;
import com.domain.base.result.AlbumResult;


import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for Movie list
 */

public class MusicLibraryPresenter implements MusicLibraryContract.Presenter {

    private final String TAG = MusicLibraryPresenter.class.getSimpleName();

    private final Context context;

    private AccountResult userSession = null;

    private final MusicLibraryContract.View view;

    private final GetUserBuyedAlbum getUserBuyedAlbum;

    @Inject
    public MusicLibraryPresenter(Context context, MusicLibraryContract.View view, GetUserBuyedAlbum getUserBuyedAlbum) {
        this.context = context;
        this.view = view;
        this.getUserBuyedAlbum = getUserBuyedAlbum;
    }

    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void getBuyedMusic() {
        view.setLoadingIndicator(true);
        getUserBuyedAlbum.execute(new DefaultObserver<List<AlbumResult>>() {
                                      @Override
                                      public void onNext(List<AlbumResult> results) {
                                          view.setLoadingIndicator(false);
                                          view.onGetBuyedMusicSuccess(results);
                                      }

                                      @Override
                                      public void onError(Throwable er) {
                                          //TODO : need show error message based on error code from BE
                                          view.setLoadingIndicator(false);
                                      }
                                  },
                GetUserBuyedAlbum.Params.forUserGetBuyedAlbum(userSession.getAuthorization(), userSession.getUid()));
    }

    @Override
    public void findBuyedMusicByTitle(String title) {

    }


}
