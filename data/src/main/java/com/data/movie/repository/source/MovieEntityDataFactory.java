package com.data.movie.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.movie.repository.source.network.MovieNetwork;
import com.data.movie.repository.source.network.NetworkMovieEntityData;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie Entity Data factory
 */

public class MovieEntityDataFactory extends AbstractEntityDataFactory {

    private final MovieNetwork movieNetwork;

    @Inject
    public MovieEntityDataFactory(MovieNetwork movieNetwork) {
        this.movieNetwork = movieNetwork;
    }


    @Override
    public MovieEntityData createData(String source) {
        return new NetworkMovieEntityData(movieNetwork);
    }
}
