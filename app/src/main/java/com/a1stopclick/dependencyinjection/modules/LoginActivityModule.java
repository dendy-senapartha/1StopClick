package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.login.LoginContract;
import com.a1stopclick.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 08/03/2019.
 * LocalLogin module for DI
 */

@Module
public class LoginActivityModule {
    private LoginContract.View view;

    public LoginActivityModule (LoginContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    LoginContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    LoginContract.Presenter providePresenter(LoginPresenter presenter)
    {
        return presenter;
    }
}
