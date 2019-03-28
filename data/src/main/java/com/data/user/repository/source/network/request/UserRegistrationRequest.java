package com.data.user.repository.source.network.request;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User Registration Request on domain module
 */

public class UserRegistrationRequest {
    public String email;
    public Boolean emailVerified;
    public String password;
    public String provider;
    public String providerId;
    public String name;
    public String dob;
    public String phone;
    public String imageUrl;

    public UserRegistrationRequest(String email, Boolean emailVerified, String password, String provider,
                                   String providerId, String name, String dob, String phone, String imageUrl) {
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }
}
