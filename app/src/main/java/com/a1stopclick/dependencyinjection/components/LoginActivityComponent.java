package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.LoginActivityModule;
import com.a1stopclick.login.LoginActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 08/03/2019.
 * Login view activity DI
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {LoginActivityModule.class})
public interface LoginActivityComponent {
    void inject (LoginActivity activity);
}
