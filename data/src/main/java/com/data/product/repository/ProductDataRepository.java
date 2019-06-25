package com.data.product.repository;

import com.data.Source;
import com.data.product.mapper.ProductListRespondMapper;
import com.data.product.repository.source.ProductEntityData;
import com.data.product.repository.source.ProductEntityDataFactory;
import com.data.product.repository.source.network.request.FindProductByTitleRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByIdRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByProductTitleRequest;
import com.data.product.repository.source.network.request.GetUserBuyedMoviesRequest;
import com.data.product.repository.source.network.request.ProductListRequest;
import com.domain.product.ProductResult;
import com.domain.product.interactor.FindMovieByTitle;
import com.domain.product.interactor.FindTrackByTitle;
import com.domain.product.interactor.FindUserBuyedMoviesByProductTitle;
import com.domain.product.interactor.GetAllMovie;
import com.domain.product.interactor.GetAllMusic;
import com.domain.product.interactor.GetUserBuyedMovies;
import com.domain.product.interactor.FindUserBuyedMoviesByProductId;
import com.domain.product.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * implementation of movie repository
 */

@Singleton
public class ProductDataRepository implements ProductRepository {

    private final ProductEntityDataFactory productEntityDataFactory;

    private final ProductListRespondMapper productListRespondMapper;

    @Inject
    public ProductDataRepository(ProductEntityDataFactory movieEntityDataFactory, ProductListRespondMapper movieListRespondMapper) {
        this.productEntityDataFactory = movieEntityDataFactory;
        this.productListRespondMapper = movieListRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private ProductEntityData createData() {
        return productEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<ProductResult>> getAllMovie(GetAllMovie.Params params) {
        return initializedRequest(createData()
                .getMovieList(new ProductListRequest(params.authorization))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params) {
        return initializedRequest(createData()
                .getMusicList(new ProductListRequest(params.authorization))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> findMovieByTitle(FindMovieByTitle.Params params) {
        return initializedRequest(createData()
                .findMovieByTitle(new FindProductByTitleRequest(params.authorization, params.title))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> findTrackByTitle(FindTrackByTitle.Params params) {
        return initializedRequest(createData()
                .findTrackByTitle(new FindProductByTitleRequest(params.authorization, params.title))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> getUserBuyedMovie(GetUserBuyedMovies.Params params) {
        return initializedRequest(createData()
                .getUserBuyedMovies(new GetUserBuyedMoviesRequest(params.authorization, params.userId))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> findUserBuyedMovieByProductID(FindUserBuyedMoviesByProductId.Params params) {
        return initializedRequest(createData()
                .findUserBuyedMovieByProductID(new FindUserBuyedMoviesByIdRequest(params.authorization, params.userId, params.productId))
                .map(productListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> findUserBuyedMovieByProductTitle(FindUserBuyedMoviesByProductTitle.Params params) {
        return initializedRequest(createData()
                .findUserBuyedMovieByProductTitle(new FindUserBuyedMoviesByProductTitleRequest(params.authorization,
                        params.userId,
                        params.productTitle))
                .map(productListRespondMapper::transform)
        );
    }
}
