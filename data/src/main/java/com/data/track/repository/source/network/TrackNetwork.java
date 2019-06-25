package com.data.track.repository.source.network;


import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;
import com.data.product.ProductEntity;
import com.data.track.TrackEntity;
import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.data.track.repository.source.network.request.FindUserBuyedSongsByAlbumIdRequest;
import com.data.track.repository.source.network.request.GetAlbumSongRequest;
import com.data.track.repository.source.network.response.FindUserBuyedSongsByAlbumIdResponse;
import com.data.track.repository.source.network.response.GetAlbumSongsResponse;
import com.data.track.repository.source.network.response.TrackListResponse;
import com.data.video.VideoEntity;
import com.data.volley.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;
/*
 * Created by dendy-prtha on 11/06/2019.
 * Track Network
 */

@Singleton
public class TrackNetwork {

    private String TAG = TrackNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public TrackNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public TrackListResponse findTrackByProductId(FindTrackByProductIdRequest request) {
        TrackListResponse response = new TrackListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("productId", request.productId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_TRACK_BY_PRODUCT_ID, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<TrackEntity>> typeRef = new TypeReference<List<TrackEntity>>() {
            };
            List<TrackEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.trackEntityList = listResponse;
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

    public GetAlbumSongsResponse getalbumSongs(GetAlbumSongRequest request) {
        GetAlbumSongsResponse response = new GetAlbumSongsResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("albumId", request.albumId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_ALBUM_SONGS, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
            };
            List<ProductEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.albumSongList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }

    public FindUserBuyedSongsByAlbumIdResponse findUserBuyedSongsByAlbumId(FindUserBuyedSongsByAlbumIdRequest request) {
        FindUserBuyedSongsByAlbumIdResponse response = new FindUserBuyedSongsByAlbumIdResponse();
        try {
            Map<String, String> params = new HashMap<>();
            params.put("userId", request.userId);
            params.put("albumId", request.albumId);
            Map<String, String> paramHeader = new HashMap<>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_USER_BUYED_SONGS_BY_ALBUMID, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<ProductEntity>> typeRef = new TypeReference<List<ProductEntity>>() {
            };
            List<ProductEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.albumSongList = listResponse;
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
}
