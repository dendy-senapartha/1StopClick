package com.a1stopclick.homeactivity.movielist;


/*
 * Created by dendy-prtha on 16/04/2019.
 * movieList contract
 */

import com.a1stopclick.base.BaseContract;
import com.domain.account.AccountResult;
import com.domain.movie.MovieListResult;

import java.util.List;

public interface MovieListContract {
    interface View
    {
        void onMovieListSuccess(List<MovieListResult> movieListResults);
        void showLoading();
        void hideLoading();
    }

    interface Presenter extends BaseContract.BasePresenterContract
    {
        void initPresenter();
        AccountResult getSession();
        void getMovieList();
    }
}
