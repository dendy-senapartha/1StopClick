package com.data.product.repository.source;

import com.data.product.repository.source.network.request.FindProductByTitleRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByIdRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByProductTitleRequest;
import com.data.product.repository.source.network.request.GetUserBuyedMoviesRequest;
import com.data.product.repository.source.network.request.ProductListRequest;
import com.data.product.repository.source.network.response.ProductListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface ProductEntityData {

    Observable<ProductListResponse> getMovieList(ProductListRequest request);

    Observable<ProductListResponse> getMusicList(ProductListRequest request);

    Observable<ProductListResponse> findMovieByTitle(FindProductByTitleRequest request);

    Observable<ProductListResponse> findTrackByTitle(FindProductByTitleRequest request);

    Observable<ProductListResponse> getUserBuyedMovies(GetUserBuyedMoviesRequest request);

    Observable<ProductListResponse> findUserBuyedMovieByProductID(FindUserBuyedMoviesByIdRequest request);

    Observable<ProductListResponse> findUserBuyedMovieByProductTitle(FindUserBuyedMoviesByProductTitleRequest request);
}
