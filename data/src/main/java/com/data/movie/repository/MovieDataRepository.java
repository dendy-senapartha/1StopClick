package com.data.movie.repository;

import com.data.Source;
import com.data.movie.mapper.MovieListRespondMapper;
import com.data.movie.repository.source.MovieEntityData;
import com.data.movie.repository.source.MovieEntityDataFactory;
import com.data.movie.repository.source.network.request.MovieListRequest;
import com.domain.base.ProductResult;
import com.domain.movie.MovieListResult;
import com.domain.movie.interactor.GetAllMovie;
import com.domain.movie.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * implementation of movie repository
 */

@Singleton
public class MovieDataRepository implements MovieRepository {

    private final MovieEntityDataFactory movieEntityDataFactory;

    private final MovieListRespondMapper movieListRespondMapper;

    @Inject
    public MovieDataRepository(MovieEntityDataFactory movieEntityDataFactory, MovieListRespondMapper movieListRespondMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.movieListRespondMapper = movieListRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private MovieEntityData createData() {
        return movieEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<ProductResult>> getAllMovie(GetAllMovie.Params params) {
        return initializedRequest(createData()
                .getMovieList(new MovieListRequest(params.authorization))
                .map(movieListRespondMapper::transform)
        );
    }
}
