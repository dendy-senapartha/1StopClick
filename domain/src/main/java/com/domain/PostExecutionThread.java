package com.domain;

import io.reactivex.Scheduler;

/*
 * Created by dendy-prtha on 27/02/2019.
 * TODO: Add a class header comment!
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
