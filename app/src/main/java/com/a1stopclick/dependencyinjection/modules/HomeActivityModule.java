package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.mainactivity.HomeContract;
import com.a1stopclick.mainactivity.HomePresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Home activity DI module
 */

@Module
public class HomeActivityModule {

    private HomeContract.View view;

    public HomeActivityModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    HomeContract.View provideView() {
        return view;
    }

    @Provides
    @PerActivity
    HomeContract.Presenter providePresenter(HomePresenter presenter) {
        return presenter;
    }
}
