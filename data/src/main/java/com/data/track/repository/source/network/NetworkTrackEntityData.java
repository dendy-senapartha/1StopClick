package com.data.track.repository.source.network;

import com.data.track.repository.source.TrackEntityData;
import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.data.track.repository.source.network.request.FindUserBuyedSongsByAlbumIdRequest;
import com.data.track.repository.source.network.request.GetAlbumSongRequest;
import com.data.track.repository.source.network.response.FindUserBuyedSongsByAlbumIdResponse;
import com.data.track.repository.source.network.response.GetAlbumSongsResponse;
import com.data.track.repository.source.network.response.TrackListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Songs entity data
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
            return trackNetwork.findTrackByProductId(request);
        });
    }

    @Override
    public Observable<GetAlbumSongsResponse> getAlbumSongs(GetAlbumSongRequest request) {
        return initObservable(()->{
            return trackNetwork.getalbumSongs(request);
        });
    }

    @Override
    public Observable<FindUserBuyedSongsByAlbumIdResponse> findUserBuyedSongsByAlbumId(FindUserBuyedSongsByAlbumIdRequest request) {
        return initObservable(()->{
            return trackNetwork.findUserBuyedSongsByAlbumId(request);
        });
    }
}
