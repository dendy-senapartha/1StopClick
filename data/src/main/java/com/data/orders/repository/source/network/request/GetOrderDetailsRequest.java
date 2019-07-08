package com.data.orders.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed album Request DTO
 */

public class GetOrderDetailsRequest {
    public String authorization;
    public String orderId;

    public GetOrderDetailsRequest(String authorization, String orderId) {
        this.authorization = authorization;
        this.orderId = orderId;
    }
}
