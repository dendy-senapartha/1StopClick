package com.domain.product.repository;

import com.domain.product.ProductResult;
import com.domain.product.interactor.CheckMovieAlreadyOrdered;
import com.domain.product.interactor.FindMovieByTitle;
import com.domain.product.interactor.FindTrackByTitle;
import com.domain.product.interactor.FindUserBuyedMoviesByProductTitle;
import com.domain.product.interactor.GetAllMovie;
import com.domain.product.interactor.GetAllMusic;
import com.domain.product.interactor.GetUserBuyedMovies;
import com.domain.product.interactor.FindUserBuyedMoviesByProductId;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie repository interface
 */

public interface ProductRepository {

    Observable<List<ProductResult>> getAllMovie(GetAllMovie.Params params);

    Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params);

    Observable<List<ProductResult>> findMovieByTitle(FindMovieByTitle.Params params);

    Observable<List<ProductResult>> findTrackByTitle(FindTrackByTitle.Params params);

    Observable<List<ProductResult>> getUserBuyedMovie(GetUserBuyedMovies.Params params);

    Observable<List<ProductResult>> findUserBuyedMovieByProductID(FindUserBuyedMoviesByProductId.Params params);

    Observable<List<ProductResult>> findUserBuyedMovieByProductTitle(FindUserBuyedMoviesByProductTitle.Params params);

    Observable<Boolean> checkMovieAlreadyOrdered(CheckMovieAlreadyOrdered.Params params);
}
