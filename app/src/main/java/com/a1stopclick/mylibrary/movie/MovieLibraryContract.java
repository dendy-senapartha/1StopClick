package com.a1stopclick.mylibrary.movie;

import com.a1stopclick.base.BaseContract.BasePresenterContract;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface MovieLibraryContract {
    interface View
    {
        void onGetBuyedMoviesSuccess(List<ProductResult> buyedMovies);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenterContract
    {
        void initPresenter();
        void getBuyedMovie();
        void findBuyedMovieByTitle(String title);
    }
}
