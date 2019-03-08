package com.a1stopclick.base;

import io.reactivex.disposables.Disposable;

/*
 * Created by dendy-prtha on 26/02/2019.
 * disposable handle disposable observer
 */
public interface DisposableHandler {

    void addDisposable(Disposable disposable);

    void dispose();
}
