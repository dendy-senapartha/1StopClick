package com.data.album.repository;

import com.data.Source;
import com.data.album.mapper.AlbumListRespondMapper;
import com.data.album.repository.source.AlbumEntityData;
import com.data.album.repository.source.AlbumEntityDataFactory;
import com.data.album.repository.source.network.request.GetAlbumListRequest;
import com.data.album.repository.source.network.request.GetUserBuyedAlbumRequest;
import com.domain.album.interactor.GetAllAlbum;
import com.domain.album.interactor.GetUserBuyedAlbum;
import com.domain.album.repository.AlbumRepository;
import com.domain.base.result.AlbumResult;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * implementation of Album repository
 */

@Singleton
public class AlbumDataRepository implements AlbumRepository {

    private final AlbumEntityDataFactory movieEntityDataFactory;

    private final AlbumListRespondMapper albumListRespondMapper;

    @Inject
    public AlbumDataRepository(AlbumEntityDataFactory movieEntityDataFactory, AlbumListRespondMapper albumListRespondMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.albumListRespondMapper = albumListRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private AlbumEntityData createData() {
        return movieEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<AlbumResult>> getAllAlbum(GetAllAlbum.Params params) {
        return initializedRequest(createData()
                .getAlbumList(new GetAlbumListRequest(params.authorization))
                .map(albumListRespondMapper::transform)
        );
    }

    @Override
    public Observable<List<AlbumResult>> getUserBuyedAlbum(GetUserBuyedAlbum.Params params) {
        return initializedRequest(createData()
                .getUserBuyedAlbum(new GetUserBuyedAlbumRequest(params.authorization, params.userId))
                .map(albumListRespondMapper::transform)
        );
    }
}
