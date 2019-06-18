package com.a1stopclick.home.movielist;

import com.a1stopclick.base.BaseContract;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * productEntityList contract
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
        void getMovieList();
        void findMovieByTitle(String title);
    }
}
