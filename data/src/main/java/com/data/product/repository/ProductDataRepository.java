package com.data.product.repository;

import com.data.Source;
import com.data.product.mapper.AlbumListRespondMapper;
import com.data.product.mapper.ProductListRespondMapper;
import com.data.product.repository.source.ProductEntityData;
import com.data.product.repository.source.ProductEntityDataFactory;
import com.data.product.repository.source.network.request.ProductListRequest;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;
import com.domain.product.interactor.GetAllAlbum;
import com.domain.product.interactor.GetAllMovie;
import com.domain.product.interactor.GetAllMusic;
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

    private final ProductEntityDataFactory movieEntityDataFactory;

    private final ProductListRespondMapper movieListRespondMapper;

    private final AlbumListRespondMapper albumListRespondMapper;

    @Inject
    public ProductDataRepository(ProductEntityDataFactory movieEntityDataFactory, ProductListRespondMapper movieListRespondMapper, AlbumListRespondMapper albumListRespondMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.movieListRespondMapper = movieListRespondMapper;
        this.albumListRespondMapper = albumListRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private ProductEntityData createData() {
        return movieEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<ProductResult>> getAllMovie(GetAllMovie.Params params) {
        return initializedRequest(createData()
                .getMovieList(new ProductListRequest(params.authorization))
                .map(movieListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params) {
        return initializedRequest(createData()
                .getMusicList(new ProductListRequest(params.authorization))
                .map(movieListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<AlbumResult>> getAllAlbum(GetAllAlbum.Params params) {
        return initializedRequest(createData()
                .getAlbumList(new ProductListRequest(params.authorization))
                .map(albumListRespondMapper::transform)
        );
    }
}
