package com.data.orders.repository;

import com.data.Source;
import com.data.orders.mapper.OrderRespondMapper;
import com.data.orders.repository.source.OrderEntityData;
import com.data.orders.repository.source.OrderEntityDataFactory;
import com.data.orders.repository.source.network.request.AddItemToOrderRequest;
import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.data.orders.repository.source.network.request.PayingOrderRequest;
import com.data.orders.repository.source.network.request.RemoveItemFromOrderRequest;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;
import com.domain.order.PayingOrderResult;
import com.domain.order.RemoveItemFromOrderResult;
import com.domain.order.interactor.AddItemToOrder;
import com.domain.order.interactor.FindOrderByUserId;
import com.domain.order.interactor.GetOrderDetails;
import com.domain.order.interactor.GetUserOrderNeedToPay;
import com.domain.order.interactor.PayingOrder;
import com.domain.order.interactor.RemoveItemFromOrder;
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

    private final OrderRespondMapper OrderRespondMapper;

    @Inject
    public OrderDataRepository(OrderEntityDataFactory movieEntityDataFactory,
                               OrderRespondMapper albumListRespondMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.OrderRespondMapper = albumListRespondMapper;
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
                .map(OrderRespondMapper::transformOrderList)
        );
    }

    @Override
    public Observable<List<OrderResult>> getUserOrderNeedToPay(GetUserOrderNeedToPay.Params params) {
        return initializedRequest(createData()
                .getUserOrderNeedToPay(new FindOrderByUserIdRequest(params.authorization, params.userId))
                .map(OrderRespondMapper::transformOrderList)
        );
    }

    @Override
    public Observable<OrderDetailResult> getOrderDetails(GetOrderDetails.Params params) {
        return initializedRequest(createData()
                .getOrderDetails(new GetOrderDetailsRequest(params.authorization, params.orderId))
                .map(OrderRespondMapper::transformOrderDetails)
        );
    }

    @Override
    public Observable<AddItemToOrderResult> addItemToOrder(AddItemToOrder.Params params) {
        return initializedRequest(createData()
                .addItemToOrder(new AddItemToOrderRequest(params.authorization, params.userId, params.productId, params.quantity))
                .map(OrderRespondMapper::transformAddItemToOrder));
    }

    @Override
    public Observable<RemoveItemFromOrderResult> removeItemFromOrder(RemoveItemFromOrder.Params params) {
        return initializedRequest(createData()
                .removeItemFromOrder(new RemoveItemFromOrderRequest(params.authorization, params.orderId, params.productId, params.quantity))
                .map(OrderRespondMapper::transformRemoveItemFromOrder));
    }

    @Override
    public Observable<PayingOrderResult> payingOrder(PayingOrder.Params params) {
        return initializedRequest(createData()
                .payingOrder(new PayingOrderRequest(params.authorization, params.userId, params.orderId, params.paymentMethodId))
                .map(OrderRespondMapper::transformPayingOrder));
    }
}
