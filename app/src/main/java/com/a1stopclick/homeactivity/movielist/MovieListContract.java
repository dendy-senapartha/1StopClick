package com.a1stopclick.homeactivity.movielist;

import com.a1stopclick.base.BaseContract;
import com.domain.account.AccountResult;
import com.domain.base.result.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movieList contract
 */

public interface MovieListContract {
    interface View
    {
        void onMovieListSuccess(List<ProductResult> movieListResults);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BaseContract.BasePresenterContract
    {
        void initPresenter();
        AccountResult getSession();
        void getMovieList();
    }
}
