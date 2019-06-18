package com.a1stopclick.mylibrary.movie;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.product.ProductResult;
import com.domain.product.interactor.FindUserBuyedMoviesByProductTitle;
import com.domain.product.interactor.GetUserBuyedMovies;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for Movie list
 */

public class MovieLibraryPresenter implements MovieLibraryContract.Presenter {

    private final String TAG = MovieLibraryPresenter.class.getSimpleName();

    private final Context context;

    private AccountResult userSession = null;

    //todo: this interactor can do same thing. need to choose which one is better
    private final GetUserBuyedMovies getBuyedMovies;

    private final FindUserBuyedMoviesByProductTitle findUserBuyedMoviesByProductTitle;

    private final MovieLibraryContract.View view;

    @Inject
    public MovieLibraryPresenter(Context context, GetUserBuyedMovies getBuyedMovies, FindUserBuyedMoviesByProductTitle findUserBuyedMoviesByProductTitle, MovieLibraryContract.View view) {
        this.context = context;
        this.getBuyedMovies = getBuyedMovies;
        this.findUserBuyedMoviesByProductTitle = findUserBuyedMoviesByProductTitle;
        this.view = view;
    }


    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void getBuyedMovie() {
        view.setLoadingIndicator(true);
        getBuyedMovies.execute(new DefaultObserver<List<ProductResult>>(){
            @Override
            public  void onNext(List<ProductResult> results){
                view.setLoadingIndicator(false);
                view.onGetBuyedMoviesSuccess(results);
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                view.setLoadingIndicator(false);
            }
        },
                GetUserBuyedMovies.Params.forUserGetBuyedMovies(userSession.getAuthorization(), userSession.getUid()));
    }

    @Override
    public void findBuyedMovieByTitle(String title) {
        view.setLoadingIndicator(true);
        findUserBuyedMoviesByProductTitle.execute(new DefaultObserver<List<ProductResult>>(){
                                   @Override
                                   public  void onNext(List<ProductResult> results){
                                       view.setLoadingIndicator(false);
                                       view.onGetBuyedMoviesSuccess(results);
                                   }

                                   @Override
                                   public void onError(Throwable er) {
                                       //TODO : need show error message based on error code from BE
                                       view.setLoadingIndicator(false);
                                   }
                               },
                FindUserBuyedMoviesByProductTitle.Params.forUserGetBuyedMoviesByProdTitle(userSession.getAuthorization(),
                        userSession.getUid(), title));
    }
}
