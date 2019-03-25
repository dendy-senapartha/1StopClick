package com.data.user.repository.source.network.request;


/*
 * Created by dendy-prtha on 21/03/2019.
 * Social Login DTO
 */

public class SocialLoginRequest {

    public String email;
    public String xidToken;
    public String provider;

    public SocialLoginRequest(String email, String xidToken, String provider)
    {
        this.provider = provider;
        this.xidToken = xidToken;
        this.email = email;
    }
}
