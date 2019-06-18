package com.data.receipt;


/*
 * Created by dendy-prtha on 10/06/2019.
 * TODO: Add a class header comment!
 */

import com.data.invoice.InvoiceEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiptEntity {

    public int id;

    public InvoiceEntity invoice;

    public PaymentMethod paymentMethod;

    public String description;

    public BigDecimal totalAmount;

    public Date created;

    public static class PaymentMethod {
        public int id;

        public String code;

        public String name;
    }
}
