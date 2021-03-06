package com.a1stopclick.home.movielist;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.product.ProductResult;
import com.domain.product.interactor.FindMovieByTitle;
import com.domain.product.interactor.GetAllMovie;
import com.domain.product.interactor.GetUserBuyedMovies;

import java.util.Iterator;
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
    private final FindMovieByTitle findMovieByTitle;
    private final GetUserBuyedMovies getBuyedMovies;
    private AccountResult userSession = null;

    @Inject
    public MovieListPresenter(Context context, MovieListContract.View view, GetAllMovie getAllMovie,
                              FindMovieByTitle findMovieByTitle, GetUserBuyedMovies getBuyedMovies) {
        this.context = context;
        this.view = view;
        this.getAllMovie = getAllMovie;
        this.findMovieByTitle = findMovieByTitle;
        this.getBuyedMovies = getBuyedMovies;
    }


    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void getMovieList() {
        view.setLoadingIndicator(true);
        getAllMovie.execute(new DefaultObserver<List<ProductResult>>() {
                                @Override
                                public void onNext(List<ProductResult> availableMovies) {
                                    getBuyedMovies.execute(new DefaultObserver<List<ProductResult>>() {
                                                               @Override
                                                               public void onNext(List<ProductResult> buyedMovies) {
                                                                   //check if movie already bought
                                                                   for (Iterator<ProductResult> availableMovieIterator= availableMovies.iterator();
                                                                        availableMovieIterator.hasNext(); ) {
                                                                       ProductResult movie = availableMovieIterator.next();
                                                                       for (Iterator<ProductResult> userBuyedMovieIterator = buyedMovies.iterator();
                                                                            userBuyedMovieIterator.hasNext(); ) {
                                                                           ProductResult userBuyedMovie = userBuyedMovieIterator.next();
                                                                           if (movie.product.id == userBuyedMovie.product.id) {
                                                                               availableMovieIterator.remove();
                                                                           }
                                                                       }
                                                                   }
                                                                   view.setLoadingIndicator(false);
                                                                   view.onMovieListSuccess(availableMovies);
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
