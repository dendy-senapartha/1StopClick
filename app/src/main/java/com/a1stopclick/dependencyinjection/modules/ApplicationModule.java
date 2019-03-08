package com.a1stopclick.dependencyinjection.modules;

import android.app.Application;
import android.content.Context;

import com.JobExecutor;
import com.a1stopclick.base.UIThread;
import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 26/02/2019.
 * Module for Application
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
