package com.domain.movie.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.movie.MovieListResult;
import com.domain.movie.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * get all movie use case
 */

public class GetAllMovie extends UseCase<List<MovieListResult>, GetAllMovie.Params> {

    private final MovieRepository movieRepository;

    @Inject
    public GetAllMovie(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<List<MovieListResult>> buildUseCaseObservable(Params params) {
        return movieRepository.getAllMovie(params);

    }

    public static class Params {
        public String authorization;
        public String provider;

        private Params(String authorization) {
            this.authorization = authorization;
        }

        public static Params forGetMovieList(String authorization) {
            return new Params(authorization);
        }
    }
}
