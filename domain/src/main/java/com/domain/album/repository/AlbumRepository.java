package com.domain.album.repository;

import com.domain.album.interactor.GetAllAlbum;
import com.domain.album.interactor.GetUserBuyedAlbum;
import com.domain.base.result.AlbumResult;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie repository interface
 */

public interface AlbumRepository {

    Observable<List<AlbumResult>> getAllAlbum(GetAllAlbum.Params params);
    Observable<List<AlbumResult>> getUserBuyedAlbum(GetUserBuyedAlbum.Params params);
}
