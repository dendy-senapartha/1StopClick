package com.a1stopclick;

import android.content.Context;
//import android.support.multidex.MultiDexApplication;

import com.a1stopclick.application.ActivityLifecycleBehavior;
import com.a1stopclick.dependencyinjection.components.ApplicationComponent;
import com.a1stopclick.dependencyinjection.components.DaggerApplicationComponent;
import com.a1stopclick.dependencyinjection.modules.ApplicationModule;

import androidx.multidex.MultiDexApplication;

/*
 * Created by dendy-prtha on 08/03/2019.
 * application class
 */

public class OneStopClickApplication extends MultiDexApplication {

    private static Context context;

    private ApplicationComponent applicationComponent;

    public void onCreate() {
        super.onCreate();
        initializeContext();
        initInjector();
        registerActivityLifecycleCallbacks(new ActivityLifecycleBehavior());
    }

    private void initInjector()
    {
        if(applicationComponent == null)
        {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        applicationComponent.inject(this);
    }

    private void initializeContext() {
        OneStopClickApplication.context = getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent()
    {
        return  applicationComponent;
    }
}
