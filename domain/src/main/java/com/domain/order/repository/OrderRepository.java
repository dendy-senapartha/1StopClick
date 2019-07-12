package com.domain.order.repository;

import com.domain.order.AddItemToOrderResult;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;
import com.domain.order.RemoveItemFromOrderResult;
import com.domain.order.interactor.AddItemToOrder;
import com.domain.order.interactor.FindOrderByUserId;
import com.domain.order.interactor.GetOrderDetails;
import com.domain.order.interactor.GetUserOrderNeedToPay;
import com.domain.order.interactor.RemoveItemFromOrder;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * track Repository interface
 */

public interface OrderRepository {
    Observable<List<OrderResult>> findOrderByUserId(FindOrderByUserId.Params params);
    Observable<List<OrderResult>> getUserOrderNeedToPay(GetUserOrderNeedToPay.Params params);
    Observable<OrderDetailResult> getOrderDetails(GetOrderDetails.Params params);
    Observable<AddItemToOrderResult> addItemToOrder(AddItemToOrder.Params params);
    Observable<RemoveItemFromOrderResult> removeItemFromOrder(RemoveItemFromOrder.Params params);
}
