package com.data.user.repository.source.network.request;


/*
 * Created by dendy-prtha on 13/03/2019.
 * User Registration Request on domain module
 */

public class UserRegistrationRequest {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dob;
    public String phone;
    public String profilePhoto;

    public UserRegistrationRequest(String username, String password, String firstName,
                                   String lastName, String dob, String phone, String profilePhoto) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
    }
}
