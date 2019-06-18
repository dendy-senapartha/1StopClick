package com.a1stopclick;

import android.content.Context;
//import android.support.multidex.MultiDexApplication;

import com.a1stopclick.application.ActivityLifecycleBehavior;
import com.a1stopclick.base.RxBus;
import com.a1stopclick.dependencyinjection.components.ApplicationComponent;
import com.a1stopclick.dependencyinjection.components.DaggerApplicationComponent;
import com.a1stopclick.dependencyinjection.modules.ApplicationModule;
import com.crashlytics.android.Crashlytics;
import com.domain.account.AccountResult;

import androidx.multidex.MultiDexApplication;

import io.fabric.sdk.android.Fabric;

/*
 * Created by dendy-prtha on 08/03/2019.
 * application class
 */

public class OneStopClickApplication extends MultiDexApplication {

    private static Context context;

    private RxBus bus;

    private ApplicationComponent applicationComponent;

    private AccountResult session;

    public void onCreate() {
        super.onCreate();
        initializeContext();
        initInjector();
        registerActivityLifecycleCallbacks(new ActivityLifecycleBehavior());
        Fabric.with(this, new Crashlytics());
        bus = new RxBus();
    }

    private void initInjector() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        applicationComponent.inject(this);
    }

    private void initializeContext() {
        OneStopClickApplication.context = getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public RxBus getBus() {
        return bus;
    }

    public AccountResult getSession()
    {
        return session;
    }

    public void setSession(AccountResult session)
    {
        this.session = session;
    }
}
