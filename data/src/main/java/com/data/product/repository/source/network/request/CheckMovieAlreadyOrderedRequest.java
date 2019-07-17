package com.data.product.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed movies Request DTO
 */

public class CheckMovieAlreadyOrderedRequest {
    public String authorization;
    public String userId;
    public String productId;

    public CheckMovieAlreadyOrderedRequest(String authorization, String userId, String productId) {
        this.authorization = authorization;
        this.userId = userId;
        this.productId = productId;
    }
}
