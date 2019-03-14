package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.UserRegistrationModule;
import com.a1stopclick.userregistration.UserRegistrationActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User Registration DI Component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = UserRegistrationModule.class)
public interface UserRegistrationComponent {
    void inject(UserRegistrationActivity activity);
}
