package com.domain.track.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.track.TrackResult;
import com.domain.track.repository.TrackRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find Track by productTitle
 */

public class FindTrackByProductId extends UseCase<List<TrackResult>, FindTrackByProductId.Params> {

    private final TrackRepository trackRepository;

    @Inject
    public FindTrackByProductId(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TrackRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.trackRepository = trackRepository;
    }

    @Override
    protected Observable<List<TrackResult>> buildUseCaseObservable(Params params) {
        return trackRepository.findTrackByProductId(params);
    }

    public static class Params {
        public String authorization;
        public String productId;

        private Params(String authorization, String productId) {
            this.authorization = authorization;
            this.productId = productId;
        }

        public static Params forFindTrackByProductId(String authorization, String productId) {
            return new Params(authorization, productId);
        }
    }
}
