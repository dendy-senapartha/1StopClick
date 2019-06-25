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
 * find Track by productTitle
 */

public class GetAlbumSong extends UseCase<List<SongResult>, GetAlbumSong.Params> {

    private final TrackRepository trackRepository;

    @Inject
    public GetAlbumSong(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TrackRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.trackRepository = trackRepository;
    }

    @Override
    protected Observable<List<SongResult>> buildUseCaseObservable(Params params) {
        return trackRepository.getAlbumSongs(params);
    }

    public static class Params {
        public String authorization;
        public String albumId;

        private Params(String authorization, String albumId) {
            this.authorization = authorization;
            this.albumId = albumId;
        }

        public static Params forGetAlbumSong(String authorization, String albumId) {
            return new Params(authorization, albumId);
        }
    }
}
