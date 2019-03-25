package com.domain.user.repository;

import com.domain.user.LoginResult;
import com.domain.user.UserRegistrationResult;
import com.domain.user.interactor.LocalLogin;
import com.domain.user.interactor.RegisterUser;
import com.domain.user.interactor.SocialLogin;

/*
 * Created by dendy-prtha on 11/03/2019.
 * repository for user
 */

import io.reactivex.Observable;

public interface UserRepository {
    Observable<LoginResult> LocalLogin(LocalLogin.Params loginRequest);

    Observable<LoginResult> SocialLogin(SocialLogin.Params loginRequest);

    Observable<UserRegistrationResult> UserRegistration(RegisterUser.Params params);

    Observable<Boolean> Logout();

    Observable<Boolean> CheckLogin();

}
