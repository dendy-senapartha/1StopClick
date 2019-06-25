package com.domain.track.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.track.SongResult;
import com.domain.track.repository.TrackRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find buyed songs by album id
 */

public class FindUserBuyedSongsByAlbumId extends UseCase<List<SongResult>, FindUserBuyedSongsByAlbumId.Params> {

    private final TrackRepository trackRepository;

    @Inject
    public FindUserBuyedSongsByAlbumId(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TrackRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.trackRepository = trackRepository;
    }

    @Override
    protected Observable<List<SongResult>> buildUseCaseObservable(Params params) {
        return trackRepository.findUserBuyedSongsByAlbumId(params);
    }

    public static class Params {
        public String authorization;
        public String userId;
        public String albumId;

        private Params(String authorization, String userId, String albumId) {
            this.authorization = authorization;
            this.userId = userId;
            this.albumId = albumId;
        }

        public static Params forFindUserBuyedSongsByAlbumId(String authorization, String userId, String albumId) {
            return new Params(authorization, userId, albumId);
        }
    }
}
