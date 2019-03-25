package com.data.user.repository.source.network;


/*
 * Created by dendy-prtha on 11/03/2019.
 * User entity data implementation on Network layer
 */

import com.data.user.repository.source.UserEntityData;
import com.data.user.repository.source.network.request.LocalLoginRequest;
import com.data.user.repository.source.network.request.SocialLoginRequest;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;


import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class NetworkUserEntityData implements UserEntityData {

    private final UserNetwork userNetwork;

    public NetworkUserEntityData(UserNetwork userNetwork) {
        this.userNetwork = userNetwork;
    }

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    @Override
    public Observable<LoginResponse> LocalLogin(LocalLoginRequest userRequest) {
        return initObservable(()->{
            return userNetwork.LocalLogin(userRequest);
        });
    }

    @Override
    public Observable<LoginResponse> SocialLogin(SocialLoginRequest userRequest) {
        return initObservable(()->{
            return userNetwork.SocialLogin(userRequest);
        });
    }

    @Override
    public Observable<UserRegistrationResponse> UserRegistration(UserRegistrationRequest request) {
        return initObservable(()->{
            return userNetwork.userRegistration(request);
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
