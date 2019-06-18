package com.data.orders;

import com.data.invoice.InvoiceEntity;
import com.data.orderitem.OrderItemEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 10/06/2019.
 * order entity
 */

public class OrderEntity {
    public int id;

    public Date orderDate;

    public BigDecimal totalAmount;

    public String status;

    public List<OrderItemEntity> itemList;

    public InvoiceEntity invoice;
}
