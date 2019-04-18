package com.data.movie.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;
import com.data.movie.MovieEntity;
import com.data.movie.repository.source.network.request.MovieListRequest;
import com.data.movie.repository.source.network.response.MovieListResponse;
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
 * Created by dendy-prtha on 16/04/2019.
 * TODO: Add a class header comment!
 */

@Singleton
public class MovieNetwork {

    private String TAG = MovieNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public MovieNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public MovieListResponse getMovieList(MovieListRequest request) {
        MovieListResponse response = new MovieListResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();

            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_MOVIE_LIST, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<MovieEntity>> typeRef = new TypeReference<List<MovieEntity>>() {};
            List<MovieEntity> listResponse= JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.movieList = listResponse;
            response.httpResponseHeader = httpResponseHeader;
            response.exception = null;
            Log.d(TAG, "Json object : " + object);
        } catch (InterruptedException | ExecutionException | JSONException e) {
            Log.e("routes", e.getMessage());
            e.printStackTrace();
            response.exception = e.getMessage();
        }
        return response;
    }
}
