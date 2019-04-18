package com.data.movie.mapper;

import com.data.movie.MovieEntity;
import com.data.movie.repository.source.network.response.MovieListResponse;
import com.domain.movie.MovieListResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response mapper
 */

@Singleton
public class MovieListRespondMapper {

    @Inject
    public MovieListRespondMapper() {

    }

    public List<MovieListResult> transform(MovieListResponse response) {
        List<MovieListResult> result = new ArrayList<>();
        if (response != null && !response.movieList.isEmpty()) {
            for(MovieEntity movieEntity : response.movieList)
            {
                MovieListResult movieListResult = new MovieListResult();
                movieListResult.id = movieEntity.id;
                movieListResult.productName = movieEntity.productName;
                movieListResult.packageCode = movieEntity.packageCode;
                movieListResult.price = movieEntity.price;
                movieListResult.description = movieEntity.description;
                movieListResult.compatibility = movieEntity.compatibility;
                movieListResult.urldownload = movieEntity.urldownload;
                movieListResult.status = movieEntity.status;
                movieListResult.created = movieEntity.created;
                //movieListResult.category = movieEntity.category;
                //movieListResult.subcategory = movieEntity.subcategory;
                result.add(movieListResult);
            }
            //result = response.movieList;
        }
        return result;
    }
}
