package com.data.music.repository.source.network;

import com.data.music.repository.source.MusicEntityData;
import com.data.music.repository.source.network.request.MusicListRequest;
import com.data.music.repository.source.network.response.MusicListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Network music entity data
 */

public class NetworkMusicEntityData implements MusicEntityData {

    private final MusicNetwork musicNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkMusicEntityData(MusicNetwork musicNetwork) {
        this.musicNetwork = musicNetwork;
    }

    @Override
    public Observable<MusicListResponse> getMovieList(MusicListRequest request) {
        return initObservable(()->{
            return musicNetwork.getMusicList(request);
        });
    }
}
