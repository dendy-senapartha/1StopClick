package com.data.orders.repository.source;

import com.data.orders.repository.source.network.request.AddItemToOrderRequest;
import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.data.orders.repository.source.network.request.PayingOrderRequest;
import com.data.orders.repository.source.network.request.RemoveItemFromOrderRequest;
import com.data.orders.repository.source.network.response.AddItemToOrderResponse;
import com.data.orders.repository.source.network.response.FindOrderByUserIdResponse;
import com.data.orders.repository.source.network.response.GetOrderIdDetailsResponse;
import com.data.orders.repository.source.network.response.PayingOrderResponse;
import com.data.orders.repository.source.network.response.RemoveItemFromOrderResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface OrderEntityData {

    Observable<FindOrderByUserIdResponse> findOrderByUserId(FindOrderByUserIdRequest getUserBuyedAlbumRequest);
    Observable<FindOrderByUserIdResponse> getUserOrderNeedToPay(FindOrderByUserIdRequest getUserBuyedAlbumRequest);
    Observable<GetOrderIdDetailsResponse> getOrderDetails(GetOrderDetailsRequest request);
    Observable<AddItemToOrderResponse> addItemToOrder(AddItemToOrderRequest request);
    Observable<RemoveItemFromOrderResponse> removeItemFromOrder(RemoveItemFromOrderRequest request);
    Observable<PayingOrderResponse> payingOrder(PayingOrderRequest request);
}
