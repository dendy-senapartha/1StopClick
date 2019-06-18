package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.home.movielist.MovieListContract;
import com.a1stopclick.home.movielist.MovieListPresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List DI Module
 */

@Module
public class MovieListModule {

    private MovieListContract.View view;

    public MovieListModule (MovieListContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    MovieListContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    MovieListContract.Presenter providePresenter(MovieListPresenter presenter)
    {
        return presenter;
    }
}
