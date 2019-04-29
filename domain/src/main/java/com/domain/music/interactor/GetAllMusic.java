package com.domain.music.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.base.ProductResult;
import com.domain.music.MusicListResult;
import com.domain.music.repository.MusicRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * getAll music interactor
 */

public class GetAllMusic extends UseCase<List<ProductResult>, GetAllMusic.Params> {

    private final MusicRepository musicRepository;

    @Inject
    public GetAllMusic(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MusicRepository musicRepository) {
        super(threadExecutor, postExecutionThread);
        this.musicRepository = musicRepository;
    }

    @Override
    protected Observable<List<ProductResult>> buildUseCaseObservable(Params params) {
        return musicRepository.getAllMusic(params);
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