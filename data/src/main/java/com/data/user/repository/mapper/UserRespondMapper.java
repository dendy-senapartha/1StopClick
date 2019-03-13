package com.data.user.repository.mapper;

import com.data.user.repository.source.network.response.LoginResponse;
import com.domain.login.LoginResult;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 11/03/2019.
 * TODO: Add a class header comment!
 */

@Singleton
public class UserRespondMapper {

    @Inject
    public UserRespondMapper() {

    }

    public LoginResult transform(LoginResponse response) {
        LoginResult result = null;
        if (response != null) {
            //TODO : need to rework on error handling
            if (response.userEntity.id != null) {
                LoginResult.UserProfile usrProfile = new LoginResult.UserProfile();
                usrProfile.id = response.userEntity.userProfile.id;
                usrProfile.firstName = response.userEntity.userProfile.firstName;
                usrProfile.lastName = response.userEntity.userProfile.lastName;
                usrProfile.dob = response.userEntity.userProfile.dob;
                usrProfile.phone = response.userEntity.userProfile.phone;
                usrProfile.profilePhoto = response.userEntity.userProfile.profilePhoto;
                result = new LoginResult(response.userEntity.id, response.userEntity.userName, usrProfile);
            } else {
                result = new LoginResult();
            }
        }
        return result;
    }
}
