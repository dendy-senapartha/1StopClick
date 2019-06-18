package com.data.user.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;

import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;

import com.data.user.UserEntity;
import com.data.user.repository.source.network.request.ForgetPasswordRequest;
import com.data.user.repository.source.network.request.Login;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.ForgetPasswordResponse;
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
    private final VolleyHandler volleyHandler;

    @Inject
    public UserNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public LoginResponse login(Login loginRequest) {
        LoginResponse response = new LoginResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", loginRequest.email);
            params.put("password", loginRequest.password);
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

    public UserRegistrationResponse userRegistration(UserRegistrationRequest request)
            throws NullPointerException {
        Log.d(TAG, "UserRegistrationResponse " + request);
        UserRegistrationResponse response = new UserRegistrationResponse();
        try {
            Map<String, String> userProfile = new HashMap<String, String>();
            userProfile.put("id", null);
            userProfile.put("name", request.name);
            userProfile.put("dob", request.dob);
            userProfile.put("phone", request.phone);
            userProfile.put("image_url", request.imageUrl);

            Map<String, Object> user = new HashMap<String, Object>();
            user.put("email", request.email);
            user.put("email_verified", request.emailVerified);
            user.put("password", request.password);
            user.put("userId", request.provider);
            user.put("provider_id", request.providerId);
            user.put("user_profile", userProfile);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.REGISTER_USER, new JSONObject(user));
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

    public ForgetPasswordResponse forgetPassword(ForgetPasswordRequest request)
            throws NullPointerException{
        ForgetPasswordResponse response = new ForgetPasswordResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", request.email);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FORGET_PASSWORD, new JSONObject(params));
            boolean status = object.getBoolean("status");
            response.status = status;
            if(object.get("error_message")!=null)
            {
                String errorMessage = object.getString("error_message");
                response.errorMessage = errorMessage;
            }
        }
        catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            response.errorMessage = e.getMessage();
        }
        return response;
    }
}