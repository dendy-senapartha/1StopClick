package com.data.user.mapper;

import com.data.user.repository.source.network.response.ForgetPasswordResponse;
import com.domain.user.ForgetPasswordResult;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget password mapper
 */

@Singleton
public class ForgetPasswordRespondMapper {

    @Inject
    public ForgetPasswordRespondMapper()
    {

    }

    public ForgetPasswordResult transform(ForgetPasswordResponse response){
        ForgetPasswordResult result = null;
        if(response != null)
        {
            result = new ForgetPasswordResult();
            result.status = response.status;
            result.errorMessage = response.errorMessage;
        }
        return result;
    }
}
