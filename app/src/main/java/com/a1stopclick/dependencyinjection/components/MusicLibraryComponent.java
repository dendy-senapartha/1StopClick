package com.a1stopclick.dependencyinjection.components;
import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MusicLibraryModule;
import com.a1stopclick.mylibrary.music.FragmentMusicLibrary;

import dagger.Component;

/*
 * Created by dendy-prtha on 18/04/2019.
 * music library List DI component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = MusicLibraryModule.class)
public interface MusicLibraryComponent {
    void inject(FragmentMusicLibrary fragment);
}
