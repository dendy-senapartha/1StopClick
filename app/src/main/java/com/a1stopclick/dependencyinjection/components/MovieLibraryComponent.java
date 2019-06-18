package com.a1stopclick.dependencyinjection.components;
import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MovieLibraryModule;
import com.a1stopclick.mylibrary.movie.FragmentMovieLibrary;

import dagger.Component;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List DI component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MovieLibraryModule.class)
public interface MovieLibraryComponent {
    void inject(FragmentMovieLibrary activity);
}
