package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.mylibrary.movie.MovieLibraryContract;
import com.a1stopclick.mylibrary.movie.MovieLibraryPresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List DI Module
 */

@Module
public class MovieLibraryModule {

    private MovieLibraryContract.View view;

    public MovieLibraryModule(MovieLibraryContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    MovieLibraryContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    MovieLibraryContract.Presenter providePresenter(MovieLibraryPresenter presenter)
    {
        return presenter;
    }
}
