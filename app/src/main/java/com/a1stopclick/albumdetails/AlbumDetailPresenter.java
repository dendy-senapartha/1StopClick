package com.a1stopclick.albumdetails;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.track.SongResult;
import com.domain.track.interactor.FindUserBuyedSongsByAlbumId;
import com.domain.track.interactor.GetAlbumSong;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class AlbumDetailPresenter implements AlbumDetailContract.Presenter {

    private final String TAG = AlbumDetailPresenter.class.getSimpleName();

    private final Context context;
    private final AlbumDetailContract.View view;
    private final GetAlbumSong getAlbumSong;
    private final FindUserBuyedSongsByAlbumId findUserBuyedSongsByAlbumId;
    private AccountResult userSession = null;


    @Inject
    public AlbumDetailPresenter(Context context, AlbumDetailContract.View view, GetAlbumSong getAlbumSong, FindUserBuyedSongsByAlbumId findUserBuyedSongsByAlbumId) {
        this.context = context;
        this.view = view;
        this.getAlbumSong = getAlbumSong;
        this.findUserBuyedSongsByAlbumId = findUserBuyedSongsByAlbumId;
    }

    @Override
    public void initPresenter() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void getAlbumSongs(String albumId) {
        getAlbumSong.execute(new DefaultObserver<List<SongResult>>() {
                                         @Override
                                         public void onNext(List<SongResult> result) {
                                             view.onGetAlbumSongsSuccess(result);
                                         }

                                         @Override
                                         public void onError(Throwable er) {
                                             //TODO : need show error message based on error code from BE
                                             view.onError(er.getMessage());
                                         }
                                     },
                GetAlbumSong.Params.forGetAlbumSong(userSession.getAuthorization(), albumId)
        );
    }

    @Override
    public void getUserBuyedAlbumSongs(String userId ,String albumId) {
        findUserBuyedSongsByAlbumId.execute(new DefaultObserver<List<SongResult>>() {
                                 @Override
                                 public void onNext(List<SongResult> result) {
                                     view.onGetUserBuyedAlbumSongs(result);
                                 }

                                 @Override
                                 public void onError(Throwable er) {
                                     //TODO : need show error message based on error code from BE
                                     view.onError(er.getMessage());
                                 }
                             },
                FindUserBuyedSongsByAlbumId.Params.forFindUserBuyedSongsByAlbumId(userSession.getAuthorization(), userId, albumId)
        );
    }


    @Override
    public void onDestroy() {

    }
}
