package com.data.invoice;

import com.data.orders.OrderEntity;
import com.data.receipt.ReceiptEntity;
import com.data.user.UserEntity;

import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * invoice entity
 */

public class InvoiceEntity {

    public int id;

    public OrderEntity order;

    public UserEntity user;

    public Date created;

    public ReceiptEntity receipt;

}
