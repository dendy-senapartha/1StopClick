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
 * Created by dendy-prtha on 27/05/2019.
 * find track by productTitle
 */

public class FindTrackByTitle extends UseCase<List<ProductResult>, FindTrackByTitle.Params> {

    private final ProductRepository productRepository;

    @Inject
    public FindTrackByTitle(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ProductRepository productRepository) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<List<ProductResult>> buildUseCaseObservable(Params params) {
        return productRepository.findTrackByTitle(params);
    }

    public static class Params {
        public String authorization;
        public String title;

        private Params(String authorization, String title) {
            this.authorization = authorization;
            this.title = title;
        }

        public static Params forFindTrackByTitle(String authorization, String title) {
            return new Params(authorization, title);
        }
    }
}
