package com.data.user.repository.source.network.request;


/*
 * Created by dendy-prtha on 21/03/2019.
 * Login DTO
 */

public class Login {

    public String email;
    public String password;
    public String xidToken;
    public String provider;

    public Login(String email, String password, String xidToken, String provider)
    {
        this.provider = provider;
        this.password = password;
        this.xidToken = xidToken;
        this.email = email;
    }
}
