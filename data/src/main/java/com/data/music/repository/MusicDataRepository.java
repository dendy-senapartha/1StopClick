package com.data.music.repository;

import com.data.Source;
import com.data.music.mapper.MusicListRespondMapper;
import com.data.music.repository.source.MusicEntityData;
import com.data.music.repository.source.MusicEntityDataFactory;
import com.data.music.repository.source.network.request.MusicListRequest;
import com.domain.base.ProductResult;
import com.domain.music.MusicListResult;
import com.domain.music.interactor.GetAllMusic;
import com.domain.music.repository.MusicRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music repository implementation
 */

@Singleton
public class MusicDataRepository implements MusicRepository {

    private final MusicEntityDataFactory musicEntityDataFactory;

    private final MusicListRespondMapper musicListRespondMapper;

    @Inject
    public MusicDataRepository(MusicEntityDataFactory musicEntityDataFactory, MusicListRespondMapper musicListRespondMapper) {
        this.musicEntityDataFactory = musicEntityDataFactory;
        this.musicListRespondMapper = musicListRespondMapper;
    }


    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }

    private MusicEntityData createData() {
        return musicEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params) {
        return initializedRequest(createData()
                .getMovieList(new MusicListRequest(params.authorization))
                .map(musicListRespondMapper::transform)
        );
    }
}
