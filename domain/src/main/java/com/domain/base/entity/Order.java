package com.domain.base.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 10/06/2019.
 * order entity
 */

public class Order {
    public int id;

    public Date orderDate;

    public BigDecimal totalAmount;

    public String status;

    public List<OrderItem> itemList;

    public Invoice invoice;
}
