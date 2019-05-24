package com.data.product.repository.source;

import com.data.product.repository.source.network.request.ProductListRequest;
import com.data.product.repository.source.network.response.AlbumListResponse;
import com.data.product.repository.source.network.response.ProductListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface ProductEntityData {

    Observable<ProductListResponse> getMovieList(ProductListRequest request);

    Observable<ProductListResponse> getMusicList(ProductListRequest request);

    Observable<AlbumListResponse> getAlbumList(ProductListRequest productListRequest);
}
