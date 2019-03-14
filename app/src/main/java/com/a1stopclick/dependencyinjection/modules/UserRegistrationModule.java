package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.userregistration.UserRegistrationContract;
import com.a1stopclick.userregistration.UserRegistrationPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User Registration DI module
 */

@Module
public class UserRegistrationModule {

    private UserRegistrationContract.View view;

    public UserRegistrationModule (UserRegistrationContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    UserRegistrationContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    UserRegistrationContract.Presenter providePresenter(UserRegistrationPresenter presenter)
    {
        return presenter;
    }

}
