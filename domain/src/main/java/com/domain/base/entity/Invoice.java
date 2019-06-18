package com.domain.base.entity;

import com.domain.user.LoginResult;

import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * invoice entity
 */

public class Invoice {

    public int id;

    public Order order;

    public LoginResult user;

    public Date created;

    public Receipt receipt;

}
