package com.data.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;
/*
 * Created by dendy-prtha on 25/03/2019.
 * Handler for Volley connection
 */

@Singleton
public class VolleyHandler {

    private static RequestQueue mRequestQueue = null;

    private static Context context = null;

    public static final String HEADERS = "HEADERS";

    @Inject
    public VolleyHandler(Context context) {
        this.context = context;
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public JSONObject postRouteDataObject(String Url, JSONObject paramRequestBody, Map<String, String> paramRequestHeader) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Url, paramRequestBody, future, future) {
            public Map<String, String> getHeaders() {
                return paramRequestHeader;
            }

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put(HEADERS, new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        getRequestQueue().add(req);
        return future.get();
    }

    public JSONObject postRouteDataObject(String Url, JSONObject paramRequestBody) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, Url, paramRequestBody, future, future) {

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put(HEADERS, new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        getRequestQueue().add(req);
        return future.get();
    }

    private JSONObject getRouteDataObject(String Url, JSONObject paramRequestBody, Map<String, String> paramRequestHeader) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, paramRequestBody, future, future) {
            public Map<String, String> getHeaders() {
                return paramRequestHeader;
            }

            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put(HEADERS, new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        getRequestQueue().add(req);
        return future.get();
    }

    private JSONObject getRouteDataObject(String Url, JSONObject paramRequestBody) throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Url, paramRequestBody, future, future) {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put(HEADERS, new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        getRequestQueue().add(req);
        return future.get();
    }

    private JSONArray getRouteDataArray(String Url) throws ExecutionException, InterruptedException {
        RequestFuture<JSONArray> future2 = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Url, null, future2, future2);
        getRequestQueue().add(request);
        return future2.get();
    }


    private JSONArray postRouteDataArray(String Url) throws ExecutionException, InterruptedException {
        RequestFuture<JSONArray> future2 = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, Url, null, future2, future2);
        getRequestQueue().add(request);
        return future2.get();
    }
}
