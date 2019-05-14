package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MovieDetailsModule;
import com.a1stopclick.homeactivity.moviedetails.MovieDetailActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {MovieDetailsModule.class})
public interface MovieDetailsComponent {
    void inject (MovieDetailActivity activity);
}
