package com.domain.product.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.base.result.AlbumResult;
import com.domain.product.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * getAll music interactor
 */

public class GetAllAlbum extends UseCase<List<AlbumResult>, GetAllAlbum.Params> {

    private final ProductRepository productRepository;

    @Inject
    public GetAllAlbum(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ProductRepository productRepository) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<List<AlbumResult>> buildUseCaseObservable(Params params) {
        return productRepository.getAllAlbum(params);
    }

    public static class Params {
        public String authorization;

        private Params(String authorization) {
            this.authorization = authorization;
        }

        public static Params forGetAlbumList(String authorization) {
            return new Params(authorization);
        }
    }
}