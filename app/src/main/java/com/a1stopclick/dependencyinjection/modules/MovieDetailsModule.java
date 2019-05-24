package com.a1stopclick.dependencyinjection.modules;


import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.homeactivity.movielist.moviedetails.MovieDetailContract;
import com.a1stopclick.homeactivity.movielist.moviedetails.MovieDetailPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail module
 */

@Module
public class MovieDetailsModule {

    private MovieDetailContract.View view;

    public MovieDetailsModule (MovieDetailContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    MovieDetailContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    MovieDetailContract.Presenter providePresenter(MovieDetailPresenter presenter)
    {
        return presenter;
    }
}
