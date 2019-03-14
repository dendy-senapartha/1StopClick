package com.domain.user;


/*
 * Created by dendy-prtha on 11/03/2019.
 * Login Result
 */

public class LoginResult {
    public String uid;
    public String username;
    public UserProfile userProfile;

    public LoginResult()
    {

    }

    public LoginResult(String uid, String username, UserProfile userProfile)
    {
        this.uid = uid;
        this.username = username;
        this.userProfile = userProfile;
    }

    public static class UserProfile
    {
        public String id;
        public String firstName;
        public String lastName;
        public String dob;
        public String phone;
        public String profilePhoto;
    }
}
