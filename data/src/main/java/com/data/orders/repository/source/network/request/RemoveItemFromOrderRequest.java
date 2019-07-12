package com.data.orders.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * remove item from order Request DTO
 */

public class RemoveItemFromOrderRequest {
    public String authorization;
    public String orderId;
    public OrderItem orderItem;


    public RemoveItemFromOrderRequest(String authorization, String orderId, int productId, int quantity) {
        this.authorization = authorization;
        this.orderId = orderId;
        this.orderItem = new OrderItem();
        this.orderItem.productId = productId;
        this.orderItem.quantity = quantity;
    }

    public class OrderItem {
        public int productId;
        public int quantity;
    }
}
