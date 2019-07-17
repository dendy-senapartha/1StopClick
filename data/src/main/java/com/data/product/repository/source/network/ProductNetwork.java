package com.data.product.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;
import com.data.product.ProductEntity;
import com.data.product.repository.source.network.request.CheckMovieAlreadyOrderedRequest;
import com.data.product.repository.source.network.request.FindProductByTitleRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByIdRequest;
import com.data.product.repository.source.network.request.FindUserBuyedMoviesByProductTitleRequest;
import com.data.product.repository.source.network.request.GetUserBuyedMoviesRequest;
import com.data.product.repository.source.network.request.ProductListRequest;
import com.data.product.repository.source.network.response.CheckMovieAlreadyOrderedResponse;
import com.data.product.repository.source.network.response.ProductListResponse;
import com.data.volley.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 16/04/2019.
 * class to retrive data from network
 */

@Singleton
public class ProductNetwork {

    private String TAG = ProductNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public ProductNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public ProductListResponse getMovieList(ProductListRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_MOVIE_LIST, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
            };
            List<ProductEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse getMusicList(ProductListRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_MUSIC_LIST, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
            };
            List<ProductEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse findMovieByTitle(FindProductByTitleRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("productTitle", request.title);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_MOVIE_BY_TITLE, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
            };
            List<ProductEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse findTrackByTitle(FindProductByTitleRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("productTitle", request.title);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_TRACK_BY_TITLE, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            List<ProductEntity> listResponse = new ArrayList<>();
            if (object.get("result") != null) {
                JSONArray objectResult = object.getJSONArray("result");
                TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
                };
                listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            }
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse getUserBuyedMovies(GetUserBuyedMoviesRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("userid", request.userId);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_USER_BUYED_MOVIES,
                    new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            List<ProductEntity> listResponse = new ArrayList<>();
            if (object.get("result") != null) {
                JSONArray objectResult = object.getJSONArray("result");
                TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
                };
                listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            }
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse findUserBuyedMoviesByProductId(FindUserBuyedMoviesByIdRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("userid", request.userId);
            params.put("productId", request.productId);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_USER_BUYED_MOVIES_BY_PRODUCT_ID,
                    new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            List<ProductEntity> listResponse = new ArrayList<>();
            if (object.get("result") != null) {
                JSONArray objectResult = object.getJSONArray("result");
                TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
                };
                listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            }
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public ProductListResponse findUserBuyedMoviesByProductName(FindUserBuyedMoviesByProductTitleRequest request) {
        ProductListResponse response = new ProductListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("userid", request.userId);
            params.put("productName", request.productTitle);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_USER_BUYED_MOVIES_BY_PRODUCT_NAME,
                    new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            List<ProductEntity> listResponse = new ArrayList<>();
            if (object.get("result") != null) {
                JSONArray objectResult = object.getJSONArray("result");
                TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
                };
                listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            }
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.productEntityList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public CheckMovieAlreadyOrderedResponse checkMovieAlreadyOrdered(CheckMovieAlreadyOrderedRequest request) {
        CheckMovieAlreadyOrderedResponse response = new CheckMovieAlreadyOrderedResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            //hardocoded
            params.put("userId", request.userId);
            params.put("productId", request.productId);

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);

            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.CHECK_MOVIE_ALREADY_ORDERED,
                    new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONObject objectResult = object.getJSONObject("result");
            response = JSON.parseObject(objectResult.toString(), CheckMovieAlreadyOrderedResponse.class);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);

            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }
}
