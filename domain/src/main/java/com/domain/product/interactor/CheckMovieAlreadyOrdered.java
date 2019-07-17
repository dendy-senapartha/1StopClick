package com.domain.product.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.product.ProductResult;
import com.domain.product.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 *
 */

public class CheckMovieAlreadyOrdered extends UseCase<Boolean, CheckMovieAlreadyOrdered.Params> {

    private final ProductRepository movieRepository;

    @Inject
    public CheckMovieAlreadyOrdered(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ProductRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Params params) {
        return movieRepository.checkMovieAlreadyOrdered(params);

    }

    public static class Params {
        public String authorization;
        public String userId;
        public String productId;

        private Params(String authorization, String userId, String productId) {
            this.authorization = authorization;
            this.userId = userId;
            this.productId = productId;
        }

        public static Params forCheckMovieAlreadyOrdered(String authorization, String userId, String productId) {
            return new Params(authorization,userId,productId);
        }
    }
}
