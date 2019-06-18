package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MusicListModule;
import com.a1stopclick.home.musiclist.FragmentMusicList;

import dagger.Component;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music List component for DI
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MusicListModule.class)
public interface MusicListComponent {
    void inject(FragmentMusicList fragment);
}
