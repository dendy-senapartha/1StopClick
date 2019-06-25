package com.data.album.repository.source;

import com.data.album.repository.source.network.request.GetAlbumListRequest;
import com.data.album.repository.source.network.request.GetUserBuyedAlbumRequest;
import com.data.album.repository.source.network.response.AlbumListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface AlbumEntityData {

    Observable<AlbumListResponse> getAlbumList(GetAlbumListRequest productListRequest);

    Observable<AlbumListResponse> getUserBuyedAlbum(GetUserBuyedAlbumRequest getUserBuyedAlbumRequest);
}
