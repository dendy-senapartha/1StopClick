package com.data.track.repository;

import com.data.Source;
import com.data.track.mapper.TrackRespondMapper;
import com.data.track.repository.source.TrackEntityData;
import com.data.track.repository.source.TrackEntityDataFactory;
import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.domain.track.TrackResult;
import com.domain.track.interactor.FindTrackByProductId;
import com.domain.track.repository.TrackRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * implementation of video repository
 */

@Singleton
public class TrackDataRepository implements TrackRepository {

    private final TrackEntityDataFactory trackEntityDataFactory;

    private final TrackRespondMapper trackRespondMapper;

    @Inject
    public TrackDataRepository(TrackEntityDataFactory videoEntityDataFactory, TrackRespondMapper videoRespondMapper) {
        this.trackEntityDataFactory = videoEntityDataFactory;
        this.trackRespondMapper = videoRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private TrackEntityData createData() {
        return trackEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<TrackResult>> findTrackByProductId(FindTrackByProductId.Params params) {
        return initializedRequest(createData()
                .findTrackByProductId(new FindTrackByProductIdRequest(params.authorization, params.productId))
                .map(trackRespondMapper::transform)
        );
    }
}
