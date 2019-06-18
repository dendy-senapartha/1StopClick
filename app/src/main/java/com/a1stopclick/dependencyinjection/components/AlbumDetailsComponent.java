package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.AlbumDetailsModule;
import com.a1stopclick.home.musiclist.albumdetails.AlbumDetailActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {AlbumDetailsModule.class})
public interface AlbumDetailsComponent {
    void inject(AlbumDetailActivity activity);
}
