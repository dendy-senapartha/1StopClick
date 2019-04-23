package com.a1stopclick.homeactivity.movielist;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.GetAccount;
import com.domain.movie.MovieListResult;
import com.domain.movie.interactor.GetAllMovie;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for Movie list
 */

public class MovieListPresenter implements MovieListContract.Presenter {

    private final String TAG = MovieListPresenter.class.getSimpleName();

    private final Context context;

    private final MovieListContract.View view;
    private final GetAllMovie getAllMovie;
    private final GetAccount getAccount;
    private AccountResult userSession = null;

    @Inject
    public MovieListPresenter(Context context, MovieListContract.View view, GetAllMovie getAllMovie, GetAccount getAccount) {
        this.context = context;
        this.view = view;
        this.getAllMovie = getAllMovie;
        this.getAccount = getAccount;
    }


    private void retrieveSession() {
        getAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                userSession = result;
                getMovieList();
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d(TAG, "onError: " + er.toString());

            }
        });
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public AccountResult getSession() {
        return userSession;
    }

    @Override
    public void getMovieList() {
        view.showLoading();
        getAllMovie.execute(new DefaultObserver<List<MovieListResult>>() {
                                @Override
                                public void onNext(List<MovieListResult> result) {
                                    view.hideLoading();
                                    view.onMovieListSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.hideLoading();
                                }
                            },
                GetAllMovie.Params.forGetMovieList(userSession.getAuthorization())
        );
    }

    @Override
    public void onDestroy() {

    }
}
