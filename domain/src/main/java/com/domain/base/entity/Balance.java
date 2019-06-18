package com.domain.base.entity;

import com.domain.user.LoginResult;

import java.math.BigDecimal;
import java.util.Date;

/*
 * Created by dendy-prtha on 10/06/2019.
 * balance entity
 */



public class Balance {

    public int id;

    public LoginResult user;

    public BigDecimal balance;

    public Date lastUsage;

    public BalanceType balanceType;

}
