package com.a1stopclick.homeactivity.musiclist;

import com.a1stopclick.base.BaseContract;
import com.domain.account.AccountResult;
import com.domain.base.ProductResult;
import com.domain.music.MusicListResult;

import java.util.List;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music list contract
 */

public interface MusicListContract {
    interface View
    {
        void onMusicListSuccess(List<ProductResult> musicListResults);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BaseContract.BasePresenterContract
    {
        void initPresenter();
        AccountResult getSession();
        void getMusicList();
    }
}
