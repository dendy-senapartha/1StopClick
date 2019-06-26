package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.appssetting.AppsSettingContract;
import com.a1stopclick.appssetting.AppsSettingPresenter;
import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.home.movielist.MovieListContract;
import com.a1stopclick.home.movielist.MovieListPresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * Apps Setting DI Module
 */

@Module
public class AppsSettingModule {

    private AppsSettingContract.View view;

    public AppsSettingModule(AppsSettingContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    AppsSettingContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    AppsSettingContract.Presenter providePresenter(AppsSettingPresenter presenter)
    {
        return presenter;
    }
}
