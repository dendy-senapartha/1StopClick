package com.data.movie.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.movie.MovieEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response container
 */

public class MovieListResponse {

    public List<MovieEntity> movieList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public MovieListResponse() {
        // Default constructor
    }
}
