package com.data.orders.repository;

import com.data.Source;
import com.data.orders.mapper.OrderRespondMapper;
import com.data.orders.repository.source.OrderEntityData;
import com.data.orders.repository.source.OrderEntityDataFactory;
import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;
import com.domain.order.interactor.FindOrderByUserId;
import com.domain.order.interactor.GetOrderDetails;
import com.domain.order.interactor.GetUserOrderNeedToPay;
import com.domain.order.repository.OrderRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * implementation of Album repository
 */

@Singleton
public class OrderDataRepository implements OrderRepository {

    private final OrderEntityDataFactory movieEntityDataFactory;

    private final OrderRespondMapper findUserOrderListRespondMapper;

    @Inject
    public OrderDataRepository(OrderEntityDataFactory movieEntityDataFactory,
                               OrderRespondMapper albumListRespondMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.findUserOrderListRespondMapper = albumListRespondMapper;
    }

    protected <T> Observable<T> initializedRequest(Observable<T> observable) {
        return observable.onErrorResumeNext(observable);
    }


    private OrderEntityData createData() {
        return movieEntityDataFactory.createData(Source.NETWORK);
    }

    @Override
    public Observable<List<OrderResult>> findOrderByUserId(FindOrderByUserId.Params params) {
        return initializedRequest(createData()
                .findOrderByUserId(new FindOrderByUserIdRequest(params.authorization, params.userId))
                .map(findUserOrderListRespondMapper::transformOrderList)
        );
    }

    @Override
    public Observable<List<OrderResult>> getUserOrderNeedToPay(GetUserOrderNeedToPay.Params params) {
        return initializedRequest(createData()
                .getUserOrderNeedToPay(new FindOrderByUserIdRequest(params.authorization, params.userId))
                .map(findUserOrderListRespondMapper::transformOrderList)
        );
    }

    @Override
    public Observable<OrderDetailResult> getOrderDetails(GetOrderDetails.Params params) {
        return initializedRequest(createData()
                .getOrderDetails(new GetOrderDetailsRequest(params.authorization, params.orderId))
                .map(findUserOrderListRespondMapper::transformOrderDetails)
        );
    }
}
