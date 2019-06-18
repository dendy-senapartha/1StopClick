package com.data;


/*
 * Created by dendy-prtha on 12/03/2019.
 * Store all BE URL
 */

public interface BEUrl {
    public static final String PREFIX_URL = "http://13.59.254.174:8080/";
    public static final String GET_ALL_USER = PREFIX_URL + "user/get-all-user";
    public static final String LOGIN = PREFIX_URL + "auth?action=user-login";
    public static final String REGISTER_USER = PREFIX_URL + "signup?action=register-user";
    public static final String FORGET_PASSWORD= PREFIX_URL + "auth?action=user-forgot-password";
    public static final String GET_MOVIE_LIST= PREFIX_URL + "movies/get-all";
    public static final String GET_MUSIC_LIST= PREFIX_URL + "musics/get-all";
    public static final String GET_ALBUM_LIST= PREFIX_URL + "musics/get-all-album";
    public static final String FIND_MOVIE_BY_TITLE= PREFIX_URL + "movies/find-by-productTitle";
    public static final String FIND_TRACK_BY_TITLE= PREFIX_URL + "musics/find-by-productTitle";
    public static final String FIND_VIDEO_BY_PRODUCT_ID= PREFIX_URL + "video/get-video-by-productId";
    public static final String FIND_TRACK_BY_PRODUCT_ID= PREFIX_URL + "video/get-track-by-productId";
    public static final String GET_USER_BUYED_MOVIES = PREFIX_URL + "movies/get-movies-of-user";
    public static final String GET_USER_BUYED_MOVIES_BY_PRODUCT_ID= PREFIX_URL + "movies/find-movies-of-user-byid";
    public static final String GET_USER_BUYED_MOVIES_BY_PRODUCT_NAME= PREFIX_URL + "movies/find-movies-of-user-product-name";
}
