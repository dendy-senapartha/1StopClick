package com.data.product.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed movies Request DTO
 */

public class GetUserBuyedMoviesRequest {
    public String authorization;
    public String userId;

    public GetUserBuyedMoviesRequest(String authorization, String userId) {
        this.authorization = authorization;
        this.userId = userId;
    }
}
