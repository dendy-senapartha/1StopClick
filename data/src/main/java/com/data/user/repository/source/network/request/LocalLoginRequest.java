package com.data.user.repository.source.network.request;

public class LocalLoginRequest {
    public String email;
    public String password;

    public LocalLoginRequest(String email, String password)
    {
        this.password = password;
        this.email = email;
    }
}
