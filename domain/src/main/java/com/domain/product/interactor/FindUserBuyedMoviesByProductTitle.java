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
 * find user buyed movie by product title use case
 */

public class FindUserBuyedMoviesByProductTitle extends UseCase<List<ProductResult>,
        FindUserBuyedMoviesByProductTitle.Params> {

    private final ProductRepository movieRepository;

    @Inject
    public FindUserBuyedMoviesByProductTitle(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                             ProductRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<List<ProductResult>> buildUseCaseObservable(Params params) {
        return movieRepository.findUserBuyedMovieByProductTitle(params);

    }

    public static class Params {
        public String authorization;
        public String userId;
        public String productTitle;

        private Params(String authorization, String userId, String productTitle) {
            this.authorization = authorization;
            this.userId = userId;
            this.productTitle = productTitle;
        }

        public static Params forUserGetBuyedMoviesByProdTitle(String authorization, String userId, String productTitle) {
            return new Params(authorization, userId, productTitle);
        }
    }
}
