package com.data.video.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * product List Request DTO
 */

public class FindVideoByProductIdRequest {
    public String authorization;
    public String productId;

    public FindVideoByProductIdRequest(String authorization, String productId) {
        this.authorization = authorization;
        this.productId = productId;
    }
}
