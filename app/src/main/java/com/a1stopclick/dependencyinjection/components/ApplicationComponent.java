package com.a1stopclick.dependencyinjection.components;

import android.app.Application;
import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.modules.ApplicationModule;
import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.account.repository.AccountRepository;
import com.domain.user.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/*
 * Created by dendy-prtha on 26/02/2019.
 * DI Component for Application
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(OneStopClickApplication application);

    void inject(BaseActivity baseActivity);

    Context context();

    Application application();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository provideUserRepository();

    AccountRepository provideAccountRepository();
}
