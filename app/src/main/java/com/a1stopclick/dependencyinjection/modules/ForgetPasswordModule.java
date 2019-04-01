package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.forgetpassword.ForgetPasswordContract;
import com.a1stopclick.forgetpassword.ForgetPasswordPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget Password Module
 */

@Module
public class ForgetPasswordModule {

    private ForgetPasswordContract.View view;

    public ForgetPasswordModule(ForgetPasswordContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ForgetPasswordContract.View provideView() {
        return view;
    }

    @Provides
    @PerActivity
    ForgetPasswordContract.Presenter providePresenter(ForgetPasswordPresenter presenter) {
        return presenter;
    }
}
