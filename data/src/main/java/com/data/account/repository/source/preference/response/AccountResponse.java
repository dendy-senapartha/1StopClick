package com.data.account.repository.source.preference.response;


/*
 * Created by dendy-prtha on 22/03/2019.
 * account Response beans
 */

public class AccountResponse {
    public String uid;
    public String email;
    public String name;
    public String provider_id;
    public String provider;
    public String avatarUrl;
    public String authorization;

    public AccountResponse(){

    }

    public AccountResponse(String uid, String email, String name, String provider_id,
                           String provider, String avatarUrl, String authorization) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.provider_id = provider_id;
        this.provider = provider;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
