package com.data.product.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.product.ProductEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response container
 */

public class ProductListResponse {

    public List<ProductEntity> movieList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public ProductListResponse() {
        // Default constructor
    }
}
