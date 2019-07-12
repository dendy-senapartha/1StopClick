package com.data.orders.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * add item to order Request DTO
 */

public class AddItemToOrderRequest {
    public String authorization;
    public String userId;
    public OrderItem orderItem;


    public AddItemToOrderRequest(String authorization, String userId, int productId, int quantity) {
        this.authorization = authorization;
        this.userId = userId;
        this.orderItem = new OrderItem();
        this.orderItem.productId = productId;
        this.orderItem.quantity = quantity;
    }

    public class OrderItem {
        public int productId;
        public int quantity;
    }
}
