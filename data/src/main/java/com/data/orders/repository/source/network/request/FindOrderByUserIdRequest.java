package com.data.orders.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed album Request DTO
 */

public class FindOrderByUserIdRequest {
    public String authorization;
    public String userId;

    public FindOrderByUserIdRequest(String authorization, String userId) {
        this.authorization = authorization;
        this.userId = userId;
    }
}
