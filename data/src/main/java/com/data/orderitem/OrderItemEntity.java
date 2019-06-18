package com.data.orderitem;


import com.data.orders.OrderEntity;
import com.data.product.ProductEntity;

import java.math.BigDecimal;

/*
 * Created by dendy-prtha on 10/06/2019.
 * Order item entity
 */

public class OrderItemEntity {
    public int id;

    public OrderEntity order;

    public ProductEntity product;

    public int quantity;

    public BigDecimal subtotal;
}
