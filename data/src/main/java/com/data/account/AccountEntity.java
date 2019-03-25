package com.data.account;


/*
 * Created by dendy-prtha on 22/03/2019.
 * Entity for our Account
 */

public class AccountEntity {
    private String email;
    private String name;
    private String provider_id;
    private String provider;
    private String avatarUrl;
    private String authorization;

    public AccountEntity() {
    }

    public AccountEntity(String email, String name, String provider_id, String provider, String avatarUrl, String authorization) {
        this.email = email;
        this.name = name;
        this.provider_id = provider_id;
        this.provider = provider;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
