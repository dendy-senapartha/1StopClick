package com.data.orders.repository.source.network;

import com.data.orders.repository.source.OrderEntityData;
import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.data.orders.repository.source.network.response.FindOrderByUserIdResponse;
import com.data.orders.repository.source.network.response.GetOrderIdDetailsResponse;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * network product entity data
 */

public class NetworkOrderEntityData implements OrderEntityData {

    private final OrderNetwork orderNetwork;

    private <T> Observable<T> initObservable(Callable<T> callable) {
        return Observable.fromCallable(callable);
    }

    public NetworkOrderEntityData(OrderNetwork productNetwork) {
        this.orderNetwork = productNetwork;
    }

    @Override
    public Observable<FindOrderByUserIdResponse> findOrderByUserId(FindOrderByUserIdRequest request) {
        return initObservable(()->{
            return orderNetwork.findOrderByUserId(request);
        });
    }

    @Override
    public Observable<FindOrderByUserIdResponse> getUserOrderNeedToPay(FindOrderByUserIdRequest request) {
        return initObservable(()->{
            return orderNetwork.getUserOrderNeedToPay(request);
        });
    }

    @Override
    public Observable<GetOrderIdDetailsResponse> getOrderDetails(GetOrderDetailsRequest request) {
        return initObservable(()->{
            return orderNetwork.getOrderDetails(request);
        });
    }
}
