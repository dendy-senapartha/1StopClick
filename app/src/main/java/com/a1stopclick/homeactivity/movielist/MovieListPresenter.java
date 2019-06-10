package com.a1stopclick.homeactivity.movielist;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.GetAccount;
import com.domain.base.result.ProductResult;
import com.domain.product.interactor.FindMovieByTitle;
import com.domain.product.interactor.GetAllMovie;

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
    private final FindMovieByTitle findMovieByTitle;
    private AccountResult userSession = null;

    @Inject
    public MovieListPresenter(Context context, MovieListContract.View view, GetAllMovie getAllMovie, GetAccount getAccount, FindMovieByTitle findMovieByTitle) {
        this.context = context;
        this.view = view;
        this.getAllMovie = getAllMovie;
        this.getAccount = getAccount;
        this.findMovieByTitle = findMovieByTitle;
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
        view.setLoadingIndicator(true);
        getAllMovie.execute(new DefaultObserver<List<ProductResult>>() {
                                @Override
                                public void onNext(List<ProductResult> result) {
                                    view.setLoadingIndicator(false);
                                    view.onMovieListSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.setLoadingIndicator(false);
                                }
                            },
                GetAllMovie.Params.forGetMovieList(userSession.getAuthorization())
        );
    }

    @Override
    public void findMovieByTitle(String title) {
        view.setLoadingIndicator(true);
        findMovieByTitle.execute(new DefaultObserver<List<ProductResult>>() {
                                @Override
                                public void onNext(List<ProductResult> result) {
                                    view.setLoadingIndicator(false);
                                    view.onMovieListSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.setLoadingIndicator(false);
                                }
                            },
                FindMovieByTitle.Params.forFindMovieByTitle(userSession.getAuthorization(), title)
        );
    }

    @Override
    public void onDestroy() {

    }
}
