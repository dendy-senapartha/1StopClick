package com.domain.login.repository;

import com.domain.login.LoginRequest;
import com.domain.login.LoginResult;

/*
 * Created by dendy-prtha on 11/03/2019.
 * repository for user
 */

import io.reactivex.Observable;

public interface UserRepository {
    Observable<LoginResult> Login(LoginRequest loginRequest);

    Observable<Boolean> Logout();
    Observable<Boolean> CheckLogin();

    //Observable<SignUpResult> SignUp(SignUpRequest signUpRequest);
}
