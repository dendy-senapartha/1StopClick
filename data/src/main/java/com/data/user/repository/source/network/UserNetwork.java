package com.data.user.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.user.UserEntity;
import com.data.user.repository.source.network.request.LoginRequest;
import com.data.user.repository.source.network.request.UserRegistrationRequest;
import com.data.user.repository.source.network.response.LoginResponse;
import com.data.user.repository.source.network.response.UserRegistrationResponse;


import org.json.JSONArray;
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
    private static RequestQueue mRequestQueue;

    @Inject
    public UserNetwork(Context context, Serializer serializer) {
        this.context = context;
        this.serializer = serializer;
    }

    public void init() {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public LoginResponse Login(LoginRequest loginRequest) throws NullPointerException {
        Log.d("SignIn", "login in wait");
        LoginResponse response = new LoginResponse();
        //TODO : need to create error handler and mapping from BE
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", loginRequest.email);
            params.put("password", loginRequest.password);
            JSONObject object = postRouteDataObject(BEUrl.LOGIN, new JSONObject(params));
            UserEntity entity = JSON.parseObject(object.toString(), UserEntity.class);
            response.userEntity = entity;
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
            userProfile.put("firstName", request.firstName);
            userProfile.put("lastName", request.lastName);
            userProfile.put("dob", request.dob);
            userProfile.put("phone", request.phone);
            userProfile.put("profilePhoto", request.profilePhoto);

            Map<String, Object> user = new HashMap<String, Object>();

            user.put("username", request.username);
            user.put("password", request.password);
            user.put("user_profile", userProfile);
            JSONObject object = postRouteDataObject(BEUrl.INSERT_USER, new JSONObject(user));
            UserEntity entity = JSON.parseObject(object.toString(), UserEntity.class);
            //response.userEntity = entity;
            response.exception = null;
            Log.d("SignIn", "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    private JSONObject getRouteDataObject(String Url) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, null, future, future);
        mRequestQueue.add(req);
        return future.get();
    }

    private JSONArray getRouteDataArray(String Url) throws ExecutionException, InterruptedException {
        RequestFuture<JSONArray> future2 = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Url, null, future2, future2);
        mRequestQueue.add(request);
        return future2.get();
    }

    private JSONObject postRouteDataObject(String Url, JSONObject jsonObj) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Url, jsonObj, future, future);
        mRequestQueue.add(req);
        return future.get();
    }

    private JSONArray postRouteDataArray(String Url) throws ExecutionException, InterruptedException {
        RequestFuture<JSONArray> future2 = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, Url, null, future2, future2);
        mRequestQueue.add(request);
        return future2.get();
    }
}