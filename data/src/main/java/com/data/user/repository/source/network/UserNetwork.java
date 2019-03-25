package com.data.user.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;

import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;

import com.data.user.UserEntity;
import com.data.user.repository.source.network.request.LocalLoginRequest;
import com.data.user.repository.source.network.request.SocialLoginRequest;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;
import com.data.volley.VolleyHandler;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;
/*
 * Created by dendy-prtha on 11/03/2019.
 * User repo source that hit from BE API using VOlley API.
 */

@Singleton
public class UserNetwork {

    private String TAG = UserNetwork.class.getSimpleName();
    private final Context context;

    private final Serializer serializer;
    //private static RequestQueue mRequestQueue;
    private final VolleyHandler volleyHandler;

    @Inject
    public UserNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public LoginResponse SocialLogin(SocialLoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", loginRequest.email);
            Map<String, String> paramHeader = new HashMap<String, String>();
            paramHeader.put("X-ID-TOKEN", loginRequest.xidToken);
            paramHeader.put("PROVIDER", loginRequest.provider);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.LOGIN, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            UserEntity entity = JSON.parseObject(object.toString(), UserEntity.class);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.userEntity = entity;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            Log.d("SignIn", "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public LoginResponse LocalLogin(LocalLoginRequest loginRequest) {
        //initChecking();
        Log.d("SignIn", "login in wait");
        LoginResponse response = new LoginResponse();
        //TODO : need to create error handler and mapping from BE
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", loginRequest.email);
            params.put("password", loginRequest.password);
            Map<String, String> paramHeader = new HashMap<String, String>();
            paramHeader.put("PROVIDER", "local");
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.LOGIN, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            UserEntity entity = JSON.parseObject(object.toString(), UserEntity.class);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.userEntity = entity;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            Log.d("SignIn", "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public UserRegistrationResponse userRegistration(UserRegistrationRequest request) throws NullPointerException {
        Log.d(TAG, "UserRegistrationResponse " + request);
        UserRegistrationResponse response = new UserRegistrationResponse();
        try {
            Map<String, String> userProfile = new HashMap<String, String>();
            userProfile.put("id", null);
            userProfile.put("name", request.firstName);
            userProfile.put("lastName", request.lastName);
            userProfile.put("dob", request.dob);
            userProfile.put("phone", request.phone);
            userProfile.put("imageUrl", request.profilePhoto);

            Map<String, Object> user = new HashMap<String, Object>();
            user.put("email", request.username);
            user.put("password", request.password);
            user.put("user_profile", userProfile);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.INSERT_USER, new JSONObject(user), null);
            String status = object.getString("result");
            response.status = status;
            response.exception = null;
            Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }


}