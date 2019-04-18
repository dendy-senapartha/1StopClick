package com.data.movie.repository.source;

import com.data.movie.repository.source.network.request.MovieListRequest;
import com.data.movie.repository.source.network.response.MovieListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface MovieEntityData {

    Observable<MovieListResponse> getMovieList(MovieListRequest request);
}
