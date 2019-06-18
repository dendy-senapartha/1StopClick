package com.domain.base.entity;

import java.math.BigDecimal;
import java.util.Date;

/*
 * Created by dendy-prtha on 10/06/2019.
 * receipt entity
 */

public class Receipt {

    public int id;

    public Invoice invoice;

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
