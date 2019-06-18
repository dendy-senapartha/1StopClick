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
 * get all movie use case
 */

public class GetUserBuyedMovies extends UseCase<List<ProductResult>, GetUserBuyedMovies.Params> {

    private final ProductRepository movieRepository;

    @Inject
    public GetUserBuyedMovies(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                              ProductRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<List<ProductResult>> buildUseCaseObservable(Params params) {
        return movieRepository.getUserBuyedMovie(params);

    }

    public static class Params {
        public String authorization;
        public String userId;

        private Params(String authorization, String userId) {
            this.authorization = authorization;
            this.userId = userId;
        }

        public static Params forUserGetBuyedMovies(String authorization, String userId) {
            return new Params(authorization, userId);
        }
    }
}
