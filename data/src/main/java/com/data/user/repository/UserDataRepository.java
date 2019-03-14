package com.data.user.repository;

import com.data.Source;
import com.data.user.repository.mapper.LoginRespondMapper;
import com.data.user.repository.mapper.UserRegistrationResponMapper;
import com.data.user.repository.source.UserEntityData;
import com.data.user.repository.source.UserEntityDataFactory;
import com.domain.user.LoginResult;
import com.domain.user.UserRegistrationResult;
import com.domain.user.interactor.Login;
import com.domain.user.interactor.RegisterUser;
import com.domain.user.repository.UserRepository;
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

    private final LoginRespondMapper loginRespondMapper;

    private final UserRegistrationResponMapper userRegistrationResponMapper;

    @Inject
    public UserDataRepository(UserEntityDataFactory userEntityDataFactory, LoginRespondMapper loginRespondMapper
            , UserRegistrationResponMapper userRegistrationResponMapper) {
        this.userEntityDataFactory = userEntityDataFactory;
        this.loginRespondMapper = loginRespondMapper;
        this.userRegistrationResponMapper = userRegistrationResponMapper;
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
    public Observable<LoginResult> Login(Login.Params loginRequest) {
        return initializedRequest(createUserData()
                .Login(new LoginRequest(loginRequest.email, loginRequest.password))
                .map(loginRespondMapper::transform)
        );
    }

    @Override
    public Observable<UserRegistrationResult> UserRegistration(RegisterUser.Params params) {
        return initializedRequest(createUserData()
                .UserRegistration(new UserRegistrationRequest(params.username, params.password,
                        params.firstName, params.lastName, params.dob, params.phone, params.profilePhoto))
                .map(userRegistrationResponMapper::transform)
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
