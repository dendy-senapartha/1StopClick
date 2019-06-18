package com.data.track.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * track List Request DTO
 */

public class FindTrackByProductIdRequest {
    public String authorization;
    public String productId;

    public FindTrackByProductIdRequest(String authorization, String productId) {
        this.authorization = authorization;
        this.productId = productId;
    }
}
