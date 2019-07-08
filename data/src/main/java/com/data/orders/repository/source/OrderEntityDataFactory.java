package com.data.orders.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.orders.repository.source.network.OrderNetwork;
import com.data.orders.repository.source.network.NetworkOrderEntityData;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Album Entity Data factory
 */

public class OrderEntityDataFactory extends AbstractEntityDataFactory {

    private final OrderNetwork movieNetwork;

    @Inject
    public OrderEntityDataFactory(OrderNetwork movieNetwork) {
        this.movieNetwork = movieNetwork;
    }


    @Override
    public OrderEntityData createData(String source) {
        return new NetworkOrderEntityData(movieNetwork);
    }
}
