package com.data.user.repository.source.network;


/*
 * Created by dendy-prtha on 11/03/2019.
 * TODO: Add a class header comment!
 */

import com.data.user.repository.source.UserEntityData;
import com.data.user.repository.source.network.request.UserRequest;
import com.data.user.repository.source.network.response.LoginResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class NetworkUserEntityData implements UserEntityData {

    private final UserNetwork userNetwork;

    public NetworkUserEntityData(UserNetwork userNetwork) {
        this.userNetwork = userNetwork;
    }

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return initializedRequest(Observable.fromCallable(callable));
    }

    private <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(initAndRetry(observable));
    }

    private <T> Function<Throwable, ? extends Observable<? extends T>>
    initAndRetry(final Observable<T> resumedObservable) {
        return (Function<Throwable, Observable<? extends T>>) throwable -> {
            if (throwable instanceof NullPointerException) {
                return init().concatMap(aBoolean -> resumedObservable);
            }
            return Observable.error(throwable);
        };
    }

    @Override
    public Observable<Boolean> init() {
        return initObservable(() -> {
            userNetwork.init();
            return true;
        });
    }

    @Override
    public Observable<LoginResponse> Login(UserRequest userRequest) {
        return initObservable(()->{
            return userNetwork.Login(userRequest);
        });
    }

    @Override
    public Observable<Boolean> CheckLogin() {
        return null;
    }

    @Override
    public Observable<Boolean> Logout() {
        return null;
    }
}
