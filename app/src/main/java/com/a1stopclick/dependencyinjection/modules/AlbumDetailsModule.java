package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.homeactivity.musiclist.albumdetails.AlbumDetailContract;
import com.a1stopclick.homeactivity.musiclist.albumdetails.AlbumDetailPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Album Detail module
 */

@Module
public class AlbumDetailsModule {

    private AlbumDetailContract.View view;

    public AlbumDetailsModule(AlbumDetailContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    AlbumDetailContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    AlbumDetailContract.Presenter providePresenter(AlbumDetailPresenter presenter)
    {
        return presenter;
    }
}
