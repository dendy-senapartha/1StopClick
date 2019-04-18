package com.data.movie.repository.source.network;

import com.data.movie.repository.source.MovieEntityData;
import com.data.movie.repository.source.network.request.MovieListRequest;
import com.data.movie.repository.source.network.response.MovieListResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie Entity Data implementation
 */

public class NetworkMovieEntityData implements MovieEntityData {

    private final MovieNetwork movieNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkMovieEntityData(MovieNetwork movieNetwork) {
        this.movieNetwork = movieNetwork;
    }

    @Override
    public Observable<MovieListResponse> getMovieList(MovieListRequest request) {
        return initObservable(()->{
            return movieNetwork.getMovieList(request);
        });
    }
}
