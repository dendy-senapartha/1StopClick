package com.data.user.mapper;

import com.data.balance.BalanceEntity;
import com.data.invoice.InvoiceEntity;
import com.data.user.repository.source.network.response.LoginResponse;
import com.domain.base.entity.Balance;
import com.domain.base.entity.BalanceType;
import com.domain.base.entity.Invoice;
import com.domain.base.entity.Receipt;
import com.domain.user.LoginResult;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 11/03/2019.
 * Mapper between LoginResponse at data modul to LoginResult at domain module
 */

@Singleton
public class LoginRespondMapper {

    @Inject
    public LoginRespondMapper() {

    }

    public LoginResult transform(LoginResponse response) {
        LoginResult result = new LoginResult();
        if (response != null) {
            //TODO : need to rework on error handling
            if (response.userEntity.id != null) {
                LoginResult.UserProfile usrProfile = new LoginResult.UserProfile();
                usrProfile.id = response.userEntity.userProfile.id;
                usrProfile.name = response.userEntity.userProfile.name;

                usrProfile.dob = response.userEntity.userProfile.dob;
                usrProfile.phone = response.userEntity.userProfile.phone;
                usrProfile.imageUrl = response.userEntity.userProfile.imageUrl;
                result = new LoginResult(response.userEntity.id, response.userEntity.email, usrProfile);
                result.emailVerified = response.userEntity.emailVerified;
                result.provider = response.userEntity.provider;
                result.providerId = response.userEntity.providerId;
                result.authToken = response.httpResponseHeader.authorization;

            }
        }
        return result;
    }
}
