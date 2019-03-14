package com.data.user.repository.source;

import com.data.user.repository.source.network.request.LoginRequest;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User Entity Data interface
 */

public interface UserEntityData {

    Observable<Boolean> init();
    Observable<LoginResponse> Login(LoginRequest userRequest);
    Observable<UserRegistrationResponse> UserRegistration(UserRegistrationRequest userRegistrationRequest);
    Observable<Boolean> CheckLogin();

    Observable<Boolean> Logout();
}
