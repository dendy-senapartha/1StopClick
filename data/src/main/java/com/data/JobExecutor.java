package com.data;

import android.support.annotation.NonNull;

import com.domain.ThreadExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 27/02/2019.
 * implementation to ThreadExecutor
 */

@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }
}