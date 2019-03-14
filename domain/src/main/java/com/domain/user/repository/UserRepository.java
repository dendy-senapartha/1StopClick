package com.domain.user.repository;

import com.domain.user.LoginResult;
import com.domain.user.UserRegistrationResult;
import com.domain.user.interactor.Login;
import com.domain.user.interactor.RegisterUser;

/*
 * Created by dendy-prtha on 11/03/2019.
 * repository for user
 */

import io.reactivex.Observable;

public interface UserRepository {
    Observable<LoginResult> Login(Login.Params loginRequest);

    Observable<UserRegistrationResult> UserRegistration(RegisterUser.Params params);

    Observable<Boolean> Logout();

    Observable<Boolean> CheckLogin();

}
