package com.a1stopclick.dependencyinjection.components;
import com.a1stopclick.appssetting.FragmentAppsSetting;
import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.AppsSettingModule;
import com.a1stopclick.dependencyinjection.modules.MovieListModule;
import com.a1stopclick.home.movielist.FragmentMovieList;

import dagger.Component;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie List DI component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AppsSettingModule.class)
public interface AppsSettingComponent {
    void inject(FragmentAppsSetting fragment);
}
