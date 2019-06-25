package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.mylibrary.movie.MovieLibraryContract;
import com.a1stopclick.mylibrary.movie.MovieLibraryPresenter;
import com.a1stopclick.mylibrary.music.MusicLibraryContract;
import com.a1stopclick.mylibrary.music.MusicLibraryPresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List DI Module
 */

@Module
public class MusicLibraryModule {

    private MusicLibraryContract.View view;

    public MusicLibraryModule(MusicLibraryContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    MusicLibraryContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    MusicLibraryContract.Presenter providePresenter(MusicLibraryPresenter presenter)
    {
        return presenter;
    }
}
