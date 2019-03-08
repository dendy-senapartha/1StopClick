package com.a1stopclick.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/*
 * Created by dendy-prtha on 25/02/2019.
 * a callback implementation for application lifecycle. with this class, we can control activity
 * behavior
 */
public class ActivityLifecycleBehavior implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
