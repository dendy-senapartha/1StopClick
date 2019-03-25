package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.HomeActivityModule;
import com.a1stopclick.homeactivity.HomeActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Di for home activity
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {HomeActivityModule.class})
public interface HomeActivityComponent {
    void inject(HomeActivity activity);
}
