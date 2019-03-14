package com.data.user.repository.source.network.request;

public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password)
    {
        this.password = password;
        this.email = email;
    }
}
