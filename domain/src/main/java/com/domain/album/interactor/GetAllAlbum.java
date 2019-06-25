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
 * Created by dendy-prtha on 26/04/2019.
 * getAll Album interactor
 */

public class GetAllAlbum extends UseCase<List<AlbumResult>, GetAllAlbum.Params> {

    private final AlbumRepository albumRepository;

    @Inject
    public GetAllAlbum(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, AlbumRepository productRepository) {
        super(threadExecutor, postExecutionThread);
        this.albumRepository = productRepository;
    }

    @Override
    protected Observable<List<AlbumResult>> buildUseCaseObservable(Params params) {
        return albumRepository.getAllAlbum(params);
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