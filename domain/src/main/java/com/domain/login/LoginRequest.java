package com.domain.login;

/*
 * Created by dendy-prtha on 11/03/2019.
 * Login Request
 */

public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
