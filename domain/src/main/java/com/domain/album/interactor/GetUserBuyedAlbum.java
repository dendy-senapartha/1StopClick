package com.domain.album.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.album.repository.AlbumRepository;
import com.domain.base.result.AlbumResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * get all movie use case
 */

public class GetUserBuyedAlbum extends UseCase<List<AlbumResult>, GetUserBuyedAlbum.Params> {

    private final AlbumRepository productRepository;

    @Inject
    public GetUserBuyedAlbum(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             AlbumRepository productRepository) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<List<AlbumResult>> buildUseCaseObservable(Params params) {
        return productRepository.getUserBuyedAlbum(params);

    }

    public static class Params {
        public String authorization;
        public String userId;

        private Params(String authorization, String userId) {
            this.authorization = authorization;
            this.userId = userId;
        }

        public static Params forUserGetBuyedAlbum(String authorization, String userId) {
            return new Params(authorization, userId);
        }
    }
}
