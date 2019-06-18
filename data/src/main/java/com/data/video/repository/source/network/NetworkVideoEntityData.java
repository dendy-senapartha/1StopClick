package com.data.video.repository.source.network;

import com.data.video.repository.source.VideoEntityData;
import com.data.video.repository.source.network.request.FindVideoByProductIdRequest;
import com.data.video.repository.source.network.response.VideoListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Netwrok video entity data
 */

public class NetworkVideoEntityData implements VideoEntityData {

    private final VideoNetwork videoNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkVideoEntityData(VideoNetwork videoNetwork) {
        this.videoNetwork = videoNetwork;
    }

    @Override
    public Observable<VideoListResponse> findVideoByProductId(FindVideoByProductIdRequest request) {
        return initObservable(()->{
            return videoNetwork.FindVideoByProductId(request);
        });
    }
}
