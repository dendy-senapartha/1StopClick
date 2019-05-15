package com.data.product.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.product.repository.source.network.ProductNetwork;
import com.data.product.repository.source.network.NetworkProductEntityData;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie Entity Data factory
 */

public class ProductEntityDataFactory extends AbstractEntityDataFactory {

    private final ProductNetwork movieNetwork;

    @Inject
    public ProductEntityDataFactory(ProductNetwork movieNetwork) {
        this.movieNetwork = movieNetwork;
    }


    @Override
    public ProductEntityData createData(String source) {
        return new NetworkProductEntityData(movieNetwork);
    }
}
