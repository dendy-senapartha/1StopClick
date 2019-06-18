package com.data.track.repository.source.network;

import com.data.track.repository.source.TrackEntityData;
import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.data.track.repository.source.network.response.TrackListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Netwrok track entity data
 */

public class NetworkTrackEntityData implements TrackEntityData {

    private final TrackNetwork trackNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkTrackEntityData(TrackNetwork trackNetwork) {
        this.trackNetwork = trackNetwork;
    }

    @Override
    public Observable<TrackListResponse> findTrackByProductId(FindTrackByProductIdRequest request) {
        return initObservable(()->{
            return trackNetwork.FindTrackByProductId(request);
        });
    }
}
