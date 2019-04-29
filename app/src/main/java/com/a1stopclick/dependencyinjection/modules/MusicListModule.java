package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.homeactivity.musiclist.MusicListContract;
import com.a1stopclick.homeactivity.musiclist.MusicListPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music List Module for DI
 */

@Module
public class MusicListModule {

    private MusicListContract.View view;

    public MusicListModule(MusicListContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    MusicListContract.View provideView() {
        return view;
    }

    @Provides
    @PerActivity
    MusicListContract.Presenter providePresenter(MusicListPresenter presenter) {
        return presenter;
    }
}
