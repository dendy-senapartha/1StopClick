package com.domain.product.repository;

import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;
import com.domain.product.interactor.GetAllAlbum;
import com.domain.product.interactor.GetAllMovie;
import com.domain.product.interactor.GetAllMusic;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie repository interface
 */

public interface ProductRepository {

    Observable<List<ProductResult>> getAllMovie(GetAllMovie.Params params);

    Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params);

    Observable<List<AlbumResult>> getAllAlbum(GetAllAlbum.Params params);
}
