package com.data.orders;

import com.data.invoice.InvoiceEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 10/06/2019.
 * order entity
 */

public class OrderDetailsEntity {

    public int id;

    public Date orderDate;

    public BigDecimal totalAmount;

    public List<OrderItem> orderItemList;

    public InvoiceEntity invoice;

    public class OrderItem {
        public String productId;
        public String productName;
        public String quantity;
        public String subtotal;
    }
}
