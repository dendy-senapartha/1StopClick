package com.data.user.mapper;

import com.data.user.repository.source.network.response.UserRegistrationResponse;
import com.domain.user.UserRegistrationResult;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 13/03/2019.
 * Mapper between UserRegistrationRespond ata data modul to UserRegistrationResult at domain module
 */

@Singleton
public class UserRegistrationResponMapper {

    @Inject
    public UserRegistrationResponMapper() {

    }

    public UserRegistrationResult transform(UserRegistrationResponse response) {
        UserRegistrationResult result = new UserRegistrationResult();
        if (response != null) {
            result.status = Boolean.parseBoolean(response.status);
            result.errorMessage = response.errorMessage;
        } else {
            result = new UserRegistrationResult();
        }
        return result;
    }
}
