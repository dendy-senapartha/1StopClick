package com.data.account.mapper;

import com.data.account.repository.source.preference.response.AccountResponse;
import com.domain.account.AccountResult;

import javax.inject.Inject;
import javax.inject.Singleton;
/*
 * Created by dendy-prtha on 22/03/2019.
 * account mapper from BE responde to domain result
 */

@Singleton
public class AccountRespondMapper {

    @Inject
    public AccountRespondMapper() {

    }


    public AccountResult transform(AccountResponse response) {
        AccountResult result = null;
        if(response !=null)
        {
            result = new AccountResult();
            result.setUid(response.uid);
            result.setEmail(response.email);
            result.setName(response.name);
            result.setProvider_id(response.provider_id);
            result.setProvider(response.provider);
            result.setAvatarUrl(response.avatarUrl);
            result.setAuthorization(response.authorization);
        }
        return result;
    }
}
