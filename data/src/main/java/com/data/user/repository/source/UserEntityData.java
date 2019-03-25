package com.data.user.repository.source;

import com.data.user.repository.source.network.request.LocalLoginRequest;
import com.data.user.repository.source.network.request.SocialLoginRequest;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User Entity Data interface
 */

public interface UserEntityData {

    //Observable<Boolean> init();
    Observable<LoginResponse> LocalLogin(LocalLoginRequest userRequest);
    Observable<LoginResponse> SocialLogin(SocialLoginRequest userRequest);
    Observable<UserRegistrationResponse> UserRegistration(UserRegistrationRequest userRegistrationRequest);
    Observable<Boolean> CheckLogin();

    Observable<Boolean> Logout();
}
