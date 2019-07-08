package com.domain.order;

import com.domain.base.entity.Invoice;
import com.domain.base.entity.Order;
import com.domain.base.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Result for order
 */

public class OrderDetailResult {
    public int id;

    public Date orderDate;

    public BigDecimal totalAmount;

    public List<OrderItem> orderItemList;

    public Invoice invoice;
}
