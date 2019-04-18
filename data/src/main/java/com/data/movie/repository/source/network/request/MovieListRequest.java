package com.data.movie.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List Request DTO
 */

public class MovieListRequest {
    public String authorization;

    public MovieListRequest(String authorization)
    {
        this.authorization = authorization;
    }
}
