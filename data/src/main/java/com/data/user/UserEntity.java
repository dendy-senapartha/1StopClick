package com.data.user;

/*
 * Created by dendy-prtha on 11/03/2019.
 * User entitiy, representation of BE response
 */

public class UserEntity {

    public String id;
    public String userName;
    public String password;
    public UserProfile userProfile;

    public class UserProfile
    {
        public String id;
        public String firstName;
        public String lastName;
        public String dob;
        public String phone;
        public String profilePhoto;
    }

}
