package com.domain.base.entity;

import java.math.BigDecimal;

/*
 * Created by dendy-prtha on 10/06/2019.
 * Order item entity
 */

public class OrderItem {
    public int id;

    public Order order;

    public Product product;

    public int quantity;

    public BigDecimal subtotal;
}
