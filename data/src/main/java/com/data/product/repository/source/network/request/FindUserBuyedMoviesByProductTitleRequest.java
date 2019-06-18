package com.data.product.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed movies Request DTO
 */

public class FindUserBuyedMoviesByProductTitleRequest {
    public String authorization;
    public String userId;
    public String productTitle;

    public FindUserBuyedMoviesByProductTitleRequest(String authorization, String userId, String productTitle) {
        this.authorization = authorization;
        this.userId = userId;
        this.productTitle = productTitle;
    }
}
