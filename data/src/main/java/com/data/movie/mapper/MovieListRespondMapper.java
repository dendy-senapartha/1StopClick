package com.data.movie.mapper;

import com.data.movie.MovieEntity;
import com.data.movie.repository.source.network.response.MovieListResponse;
import com.domain.base.ProductResult;
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

    public List<ProductResult> transform(MovieListResponse response) {
        List<ProductResult> result = new ArrayList<>();
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
                movieListResult.productArt= movieEntity.productArt;

                movieListResult.category = new ProductResult.Category();
                movieListResult.category.id = movieEntity.category.id;
                movieListResult.category.name = movieEntity.category.name;
                movieListResult.category.isActive = movieEntity.category.isActive;
                movieListResult.category.created = movieEntity.category.created;
                movieListResult.category.target = movieEntity.category.target;
                movieListResult.category.priority = movieEntity.category.priority;

                movieListResult.subcategory = new ProductResult.Subcategory();
                movieListResult.subcategory.id = movieEntity.subcategory.id;
                movieListResult.subcategory.name = movieEntity.subcategory.name;
                movieListResult.subcategory.created = movieEntity.subcategory.created;
                movieListResult.subcategory.isActive = movieEntity.subcategory.isActive;
                movieListResult.subcategory.target = movieEntity.subcategory.target;
                movieListResult.subcategory.priority = movieEntity.subcategory.priority;

                result.add(movieListResult);
            }
            //result = response.movieList;
        }
        return result;
    }
}
