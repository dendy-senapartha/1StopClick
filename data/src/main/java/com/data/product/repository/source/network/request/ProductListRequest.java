package com.data.product.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * product List Request DTO
 */

public class ProductListRequest {
    public String authorization;

    public ProductListRequest(String authorization)
    {
        this.authorization = authorization;
    }
}
