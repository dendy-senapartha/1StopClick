package com.data.orders.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019. *
 */

public class PayingOrderRequest {
    public String authorization;
    public String userId;
    public String orderId;
    public String paymentMethodId;


    public PayingOrderRequest(String authorization, String userId, String orderId, String paymentMethodId) {
        this.authorization = authorization;
        this.userId = userId;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
    }

    public class OrderItem {
        public int productId;
        public int quantity;
    }
}
