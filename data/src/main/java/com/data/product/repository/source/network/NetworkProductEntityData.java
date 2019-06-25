package com.data.product.repository.source.network;

import com.data.product.repository.source.ProductEntityData;
import com.data.product.repository.source.network.request.FindProductByTitleRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByIdRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByProductTitleRequest;
import com.data.product.repository.source.network.request.GetUserBuyedMoviesRequest;
import com.data.product.repository.source.network.request.ProductListRequest;
import com.data.product.repository.source.network.response.ProductListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * network product entity data
 */

public class NetworkProductEntityData implements ProductEntityData {

    private final ProductNetwork productNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkProductEntityData(ProductNetwork productNetwork) {
        this.productNetwork = productNetwork;
    }

    @Override
    public Observable<ProductListResponse> getMovieList(ProductListRequest request) {
        return initObservable(()->{
            return productNetwork.getMovieList(request);
        });
    }

    @Override
    public Observable<ProductListResponse> getMusicList(ProductListRequest request) {
        return initObservable(()->{
            return productNetwork.getMusicList(request);
        });
    }

    @Override
    public Observable<ProductListResponse> findMovieByTitle(FindProductByTitleRequest request) {
        return initObservable(()->{
            return productNetwork.findMovieByTitle(request);
        });
    }

    @Override
    public Observable<ProductListResponse> findTrackByTitle(FindProductByTitleRequest request) {
        return initObservable(()->{
            return productNetwork.findTrackByTitle(request);
        });
    }

    @Override
    public Observable<ProductListResponse> getUserBuyedMovies(GetUserBuyedMoviesRequest request) {
        return initObservable(()->{
            return productNetwork.getUserBuyedMovies(request);
        });
    }

    @Override
    public Observable<ProductListResponse> findUserBuyedMovieByProductID(FindUserBuyedMoviesByIdRequest request) {
        return initObservable(()->{
            return productNetwork.findUserBuyedMoviesByProductId(request);
        });
    }

    @Override
    public Observable<ProductListResponse> findUserBuyedMovieByProductTitle(FindUserBuyedMoviesByProductTitleRequest request) {
        return initObservable(()->{
            return productNetwork.findUserBuyedMoviesByProductName(request);
        });
    }
}
