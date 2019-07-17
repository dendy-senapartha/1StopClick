package com.data.orders.repository.source.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.data.BEUrl;
import com.data.Serializer;
import com.data.account.HTTPResponseHeader;
import com.data.orders.OrderDetailsEntity;
import com.data.orders.OrderEntity;
import com.data.orders.repository.source.network.request.AddItemToOrderRequest;
import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.data.orders.repository.source.network.request.PayingOrderRequest;
import com.data.orders.repository.source.network.request.RemoveItemFromOrderRequest;
import com.data.orders.repository.source.network.response.AddItemToOrderResponse;
import com.data.orders.repository.source.network.response.FindOrderByUserIdResponse;
import com.data.orders.repository.source.network.response.GetOrderIdDetailsResponse;
import com.data.orders.repository.source.network.response.PayingOrderResponse;
import com.data.orders.repository.source.network.response.RemoveItemFromOrderResponse;
import com.data.volley.VolleyHandler;

import org.json.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 16/04/2019.
 * class to retrive data from network
 */

@Singleton
public class OrderNetwork {

    private String TAG = OrderNetwork.class.getSimpleName();

    private final Context context;
    private final Serializer serializer;
    private final VolleyHandler volleyHandler;

    @Inject
    public OrderNetwork(Context context, Serializer serializer, VolleyHandler volleyHandler) {
        this.context = context;
        this.serializer = serializer;
        this.volleyHandler = volleyHandler;
    }

    public FindOrderByUserIdResponse findOrderByUserId(FindOrderByUserIdRequest request) {
        FindOrderByUserIdResponse response = new FindOrderByUserIdResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userId", request.userId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.FIND_ORDER_BY_USERID, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<OrderEntity>> typeRef = new TypeReference<List<OrderEntity>>() {
            };
            List<OrderEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.orderEntityList = listResponse;
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

    public FindOrderByUserIdResponse getUserOrderNeedToPay(FindOrderByUserIdRequest request) {
        FindOrderByUserIdResponse response = new FindOrderByUserIdResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userId", request.userId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_USER_ORDER_NEED_TO_PAY, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONArray objectResult = object.getJSONArray("result");
            TypeReference<List<OrderEntity>> typeRef = new TypeReference<List<OrderEntity>>() {
            };
            List<OrderEntity> listResponse = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.orderEntityList = listResponse;
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

    public GetOrderIdDetailsResponse getOrderDetails(GetOrderDetailsRequest request) {
        GetOrderIdDetailsResponse response = new GetOrderIdDetailsResponse();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("orderId", request.orderId);
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.GET_ORDER_DETAILS, new JSONObject(params), paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONObject objectResult = object.getJSONObject("result");
            TypeReference<OrderDetailsEntity> typeRef = new TypeReference<OrderDetailsEntity>() {
            };
            OrderDetailsEntity Response = JSON.parseObject(objectResult.toString(), typeRef);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            response.orderDetailsEntity = Response;
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

    public AddItemToOrderResponse addItemToOrder(AddItemToOrderRequest request) {
        AddItemToOrderResponse response = new AddItemToOrderResponse();
        try {
            JSONObject paramsBody = new JSONObject(JSON.toJSONString(request));
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.ADD_ITEM_TO_ORDER, paramsBody, paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONObject objectResult = object.getJSONObject("result");
            //TypeReference<OrderDetailsEntity> typeRef = new TypeReference<OrderDetailsEntity>() {};
            response = JSON.parseObject(objectResult.toString(), AddItemToOrderResponse.class);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            //response.status= Response;
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

    public RemoveItemFromOrderResponse removeItemFromOrder(RemoveItemFromOrderRequest request) {
        RemoveItemFromOrderResponse response = new RemoveItemFromOrderResponse();
        try {
            JSONObject paramsBody = new JSONObject(JSON.toJSONString(request));
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.REMOVE_ITEM_FROM_ORDER, paramsBody, paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONObject objectResult = object.getJSONObject("result");
            response = JSON.parseObject(objectResult.toString(), RemoveItemFromOrderResponse.class);
            HTTPResponseHeader httpResponseHeader = JSON.parseObject(objectHeader.toString(), HTTPResponseHeader.class);
            //response.status= Response;
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

    public PayingOrderResponse payingOrder(PayingOrderRequest request) {
        PayingOrderResponse response = new PayingOrderResponse();
        try {
            JSONObject paramsBody = new JSONObject(JSON.toJSONString(request));
            Map<String, String> paramHeader = new HashMap<String, String>();
            //hardcoded
            paramHeader.put("authorization", request.authorization);
            JSONObject object = volleyHandler.postRouteDataObject(BEUrl.PAYING_ORDER, paramsBody, paramHeader);
            JSONObject objectHeader = object.getJSONObject(VolleyHandler.HEADERS);
            JSONObject objectResult = object.getJSONObject("result");
            response = JSON.parseObject(objectResult.toString(), PayingOrderResponse.class);
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
