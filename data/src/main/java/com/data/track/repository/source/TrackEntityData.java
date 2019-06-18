package com.data.track.repository.source;

import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.data.track.repository.source.network.response.TrackListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video entity data
 */

public interface TrackEntityData {

    Observable<TrackListResponse> findTrackByProductId(FindTrackByProductIdRequest request);
}
