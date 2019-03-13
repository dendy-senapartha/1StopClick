package com.data.user.repository;

import com.data.Source;
import com.data.user.repository.mapper.UserRespondMapper;
import com.data.user.repository.source.UserEntityData;
import com.data.user.repository.source.UserEntityDataFactory;
import com.domain.login.LoginRequest;
import com.domain.login.LoginResult;
import com.domain.login.repository.UserRepository;
import com.data.user.repository.source.network.request.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User Data Repository
 */

@Singleton
public class UserDataRepository implements UserRepository {

    private final UserEntityDataFactory userEntityDataFactory;

    private final UserRespondMapper mapper;

    @Inject
    public UserDataRepository(UserEntityDataFactory userEntityDataFactory, UserRespondMapper mapper) {
        this.userEntityDataFactory = userEntityDataFactory;
        this.mapper = mapper;
    }

    /*Instructs an ObservableSource to pass control to another ObservableSource rather than invoking onError if it encounters an error.
     * */
    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private UserEntityData createUserData() {
        return userEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<LoginResult> Login(LoginRequest loginRequest) {
        return initializedRequest(createUserData()
                .Login(new UserRequest(loginRequest.email, loginRequest.password))
                .map(mapper::transform)
        );
    }

    @Override
    public Observable<Boolean> Logout() {
        return null;
    }

    @Override
    public Observable<Boolean> CheckLogin() {
        return null;
    }
}
