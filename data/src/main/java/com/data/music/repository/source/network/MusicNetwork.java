package com.data.music.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;
import com.data.music.MusicEntity;
import com.data.music.repository.source.network.request.MusicListRequest;
import com.data.music.repository.source.network.response.MusicListResponse;
import com.data.volley.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music network repos caller
 */

public class MusicNetwork {

    private String TAG = MusicNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public MusicNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public MusicListResponse getMusicList(MusicListRequest request) {
        MusicListResponse response = new MusicListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_MUSIC_LIST, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<MusicEntity>> typeRef = new TypeReference<List<MusicEntity>>() {};
            List<MusicEntity> listResponse= JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.musicList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            //Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e(TAG + " routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }
}
