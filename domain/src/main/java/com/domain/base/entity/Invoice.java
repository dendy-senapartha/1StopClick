package com.domain.base.entity;

import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * invoice entity
 */

public class Invoice {

    public int id;

    public PaymentMethod paymentMethod;

    public String description;

    public String status;

    public Date created;
}
