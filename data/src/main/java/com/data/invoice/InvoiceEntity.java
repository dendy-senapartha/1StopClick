package com.data.invoice;

import com.data.paymentmehtod.PaymentMethodEntity;

import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * invoice entity
 */

public class InvoiceEntity {

    public int id;

    public PaymentMethodEntity paymentMethod;

    public String description;

    public String status;

    public Date created;

}
