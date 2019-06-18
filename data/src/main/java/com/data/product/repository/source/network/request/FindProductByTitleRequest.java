package com.data.product.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * product List Request DTO
 */

public class FindProductByTitleRequest {
    public String authorization;
    public String title;

    public FindProductByTitleRequest(String authorization, String title) {
        this.authorization = authorization;
        this.title = title;
    }
}
