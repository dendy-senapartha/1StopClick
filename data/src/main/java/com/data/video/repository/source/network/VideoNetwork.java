package com.data.video.repository.source.network;


import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;

import com.data.account.HTTPResponseHeader;
import com.data.video.VideoEntity;
import com.data.video.repository.source.network.request.FindVideoByProductIdRequest;
import com.data.video.repository.source.network.response.VideoListResponse;
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
 * Video Network
 */


@Singleton
public class VideoNetwork {

    private String TAG = VideoNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public VideoNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public VideoListResponse FindVideoByProductId(FindVideoByProductIdRequest request) {
        VideoListResponse response = new VideoListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("productId", request.productId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_VIDEO_BY_PRODUCT_ID, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<VideoEntity>> typeRef = new TypeReference<List<VideoEntity>>() {
            };
            List<VideoEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.videoEntityList = listResponse;
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
