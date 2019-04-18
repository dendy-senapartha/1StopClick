package com.domain.movie.repository;

import com.domain.movie.MovieListResult;
import com.domain.movie.interactor.GetAllMovie;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie repository interface
 */

public interface MovieRepository {

    Observable<List<MovieListResult>> getAllMovie(GetAllMovie.Params params);
}
