package com.data.video.repository;

import com.data.Source;
import com.data.product.repository.source.ProductEntityData;
import com.data.video.mapper.VideoRespondMapper;
import com.data.video.repository.source.VideoEntityData;
import com.data.video.repository.source.VideoEntityDataFactory;
import com.data.video.repository.source.network.request.FindVideoByProductIdRequest;
import com.domain.video.VideoResult;
import com.domain.video.interactor.FindVideoByProductId;
import com.domain.video.repository.VideoRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * implementation of video repository
 */

@Singleton
public class VideoDataRepository implements VideoRepository {

    private final VideoEntityDataFactory videoEntityDataFactory;

    private final VideoRespondMapper videoRespondMapper;

    @Inject
    public VideoDataRepository(VideoEntityDataFactory videoEntityDataFactory, VideoRespondMapper videoRespondMapper) {
        this.videoEntityDataFactory = videoEntityDataFactory;
        this.videoRespondMapper = videoRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private VideoEntityData createData() {
        return videoEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<VideoResult>> findVideoByProductId(FindVideoByProductId.Params params) {
        return initializedRequest(createData()
                .findVideoByProductId(new FindVideoByProductIdRequest(params.authorization, params.productId))
                .map(videoRespondMapper::transform)
        );
    }
}
