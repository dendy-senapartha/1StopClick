package com.a1stopclick.mylibrary.music;

import com.a1stopclick.base.BaseContract.BasePresenterContract;
import com.domain.base.result.AlbumResult;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface MusicLibraryContract {
    interface View
    {
        void onGetBuyedMusicSuccess(List<AlbumResult> buyedMovies);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenterContract
    {
        void initPresenter();
        void getBuyedMusic();
        void findBuyedMusicByTitle(String title);
    }
}
