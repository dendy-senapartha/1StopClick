package com.data.album.repository.source.network;

import com.data.album.repository.source.AlbumEntityData;
import com.data.album.repository.source.network.request.GetAlbumListRequest;
import com.data.album.repository.source.network.request.GetUserBuyedAlbumRequest;
import com.data.album.repository.source.network.response.AlbumListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * network product entity data
 */

public class NetworkAlbumEntityData implements AlbumEntityData {

    private final AlbumNetwork productNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkAlbumEntityData(AlbumNetwork productNetwork) {
        this.productNetwork = productNetwork;
    }

    @Override
    public Observable<AlbumListResponse> getAlbumList(GetAlbumListRequest request) {
        return initObservable(()->{
            return productNetwork.getAlbumList(request);
        });
    }

    @Override
    public Observable<AlbumListResponse> getUserBuyedAlbum(GetUserBuyedAlbumRequest request) {
        return initObservable(()->{
            return productNetwork.getUserBuyedAlbum(request);
        });
    }
}
