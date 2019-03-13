package com.data.user.repository.source;

import com.data.user.repository.source.network.request.UserRequest;
import com.data.user.repository.source.network.response.LoginResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/03/2019.
 * TODO: Add a class header comment!
 */

public interface UserEntityData {

    Observable<Boolean> init();
    Observable<LoginResponse> Login(UserRequest userRequest);
    Observable<Boolean> CheckLogin();

    Observable<Boolean> Logout();
}
