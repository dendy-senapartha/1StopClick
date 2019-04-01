package com.data;


/*
 * Created by dendy-prtha on 12/03/2019.
 * Store all BE URL
 */

public interface BEUrl {
    public static final String PREFIX_URL = "http://192.168.137.1:8080/";
    public static final String GET_ALL_USER = PREFIX_URL + "user/get-all-user";
    public static final String LOGIN = PREFIX_URL + "auth?action=user-login";
    public static final String REGISTER_USER = PREFIX_URL + "signup?action=register-user";
    public static final String FORGET_PASSWORD= PREFIX_URL + "auth?action=user-forgot-password";
}
