package com.data.user;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User entitiy, representation of BE response
 */

public class UserEntity {

    public String id;
    public String email;
    public boolean emailVerified;
    public String provider;
    public String providerId;
    public String password;
    public UserProfile userProfile;

    public class UserProfile
    {
        public String id;
        public String name;
        public String dob;
        public String phone;
        public String imageUrl;
    }

}
