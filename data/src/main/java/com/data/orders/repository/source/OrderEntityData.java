package com.data.orders.repository.source;

import com.data.orders.repository.source.network.request.FindOrderByUserIdRequest;
import com.data.orders.repository.source.network.request.GetOrderDetailsRequest;
import com.data.orders.repository.source.network.response.FindOrderByUserIdResponse;
import com.data.orders.repository.source.network.response.GetOrderIdDetailsResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 16/04/2019.
 * movie Entity data
 */

public interface OrderEntityData {

    Observable<FindOrderByUserIdResponse> findOrderByUserId(FindOrderByUserIdRequest getUserBuyedAlbumRequest);
    Observable<FindOrderByUserIdResponse> getUserOrderNeedToPay(FindOrderByUserIdRequest getUserBuyedAlbumRequest);
    Observable<GetOrderIdDetailsResponse> getOrderDetails(GetOrderDetailsRequest request);
}
