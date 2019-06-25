package com.a1stopclick.albumdetails;
import com.a1stopclick.base.BaseContract;
import com.domain.track.SongResult;

import java.util.List;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie Detail Contract
 */

public interface AlbumDetailContract {
    interface View extends BaseContract.BaseViewContract {
        void onGetAlbumSongsSuccess(List<SongResult> result);
        void onGetUserBuyedAlbumSongs(List<SongResult> result);
    }

    interface Presenter extends BaseContract.BasePresenterContract{
        void initPresenter();
        void getAlbumSongs(String albumId);
        void getUserBuyedAlbumSongs( String userId, String albumId);
    }
}
