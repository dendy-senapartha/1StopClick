package com.data.user.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.user.UserEntity;

public class LoginResponse {

    public UserEntity userEntity;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public LoginResponse() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public LoginResponse(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
