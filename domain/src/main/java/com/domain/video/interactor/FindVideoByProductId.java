package com.domain.video.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.video.VideoResult;
import com.domain.video.repository.VideoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find movie by productTitle
 */

public class FindVideoByProductId extends UseCase<List<VideoResult>, FindVideoByProductId.Params> {

    private final VideoRepository videoRepository;

    @Inject
    public FindVideoByProductId(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, VideoRepository videoRepository) {
        super(threadExecutor, postExecutionThread);
        this.videoRepository = videoRepository;
    }

    @Override
    protected Observable<List<VideoResult>> buildUseCaseObservable(Params params) {
        return videoRepository.findVideoByProductId(params);
    }

    public static class Params {
        public String authorization;
        public String productId;

        private Params(String authorization, String productId) {
            this.authorization = authorization;
            this.productId = productId;
        }

        public static Params forFindVideoByProductId(String authorization, String productId) {
            return new Params(authorization, productId);
        }
    }
}
