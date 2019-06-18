package com.domain.user;

import com.domain.base.entity.Balance;
import com.domain.base.entity.Invoice;

import java.util.ArrayList;
import java.util.List;


/*
 * Created by dendy-prtha on 11/03/2019.
 * LocalLogin Result
 */

public class LoginResult {
    public String uid;
    public String email;
    public boolean emailVerified;
    public String provider;
    public String providerId;
    public UserProfile userProfile;
    public String authToken;

    public LoginResult() {

    }

    public LoginResult(String uid, String username, UserProfile userProfile) {
        this.uid = uid;
        this.email = username;
        this.userProfile = userProfile;
    }

    public static class UserProfile {
        public String id;
        public String name;
        public String dob;
        public String phone;
        public String imageUrl;
    }
}
