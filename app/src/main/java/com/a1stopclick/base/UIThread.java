package com.a1stopclick.base;

import com.domain.*;

import javax.inject.Inject;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/*
 * Created by dendy-prtha on 27/02/2019.
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {

    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
