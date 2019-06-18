package com.data.video.repository.source;

import com.data.video.repository.source.network.request.FindVideoByProductIdRequest;
import com.data.video.repository.source.network.response.VideoListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video entity data
 */

public interface VideoEntityData {

    Observable<VideoListResponse> findVideoByProductId(FindVideoByProductIdRequest request);
}
