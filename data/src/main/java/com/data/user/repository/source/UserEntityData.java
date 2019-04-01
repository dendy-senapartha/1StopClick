package com.data.user.repository.source;

import com.data.user.repository.source.network.request.ForgetPasswordRequest;
import com.data.user.repository.source.network.request.Login;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.ForgetPasswordResponse;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User Entity Data interface
 */

public interface UserEntityData {

    Observable<LoginResponse> login(Login userRequest);
    Observable<UserRegistrationResponse> UserRegistration(UserRegistrationRequest userRegistrationRequest);
    Observable<Boolean> CheckLogin();

    Observable<Boolean> Logout();

    Observable<ForgetPasswordResponse> forgetPassword(ForgetPasswordRequest request);
}
