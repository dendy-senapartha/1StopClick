package com.domain;

import io.reactivex.observers.DisposableObserver;

/*
 * Created by dendy-prtha on 27/02/2019.
 * Inherit RXJava Disposable Observer so that implementer dont have to implement all of the method
 */
public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

