package com.a1stopclick.home.musiclist;

import com.a1stopclick.base.BaseContract;
import com.domain.base.result.AlbumResult;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music list contract
 */

public interface MusicListContract {
    interface View
    {
        void onAlbumListSuccess(List<AlbumResult> albumResultList);
        void onFindTrackSuccess(List<ProductResult> productResults);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BaseContract.BasePresenterContract
    {
        void initPresenter();
        void getMusicList();
        void findTrackByTitle(String searchTrackQuery);
    }
}
