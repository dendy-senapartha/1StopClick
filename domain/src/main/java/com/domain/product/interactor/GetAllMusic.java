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
 * Created by dendy-prtha on 26/04/2019.
 * getAll music interactor
 */

public class GetAllMusic extends UseCase<List<ProductResult>, GetAllMusic.Params> {

    private final ProductRepository productRepository;

    @Inject
    public GetAllMusic(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ProductRepository productRepository) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<List<ProductResult>> buildUseCaseObservable(Params params) {
        return productRepository.getAllMusic(params);
    }

    public static class Params {
        public String authorization;

        private Params(String authorization) {
            this.authorization = authorization;
        }

        public static Params forGetMusicList(String authorization) {
            return new Params(authorization);
        }
    }
}