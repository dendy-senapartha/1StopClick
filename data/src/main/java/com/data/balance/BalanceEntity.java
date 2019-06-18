package com.data.balance;


/*
 * Created by dendy-prtha on 10/06/2019.
 * TODO: Add a class header comment!
 */

import com.data.balancetype.BalanceTypeEntity;
import com.data.user.UserEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceEntity {

    public int id;

    public UserEntity user;

    public BigDecimal balance;

    public Date lastUsage;

    public BalanceTypeEntity balanceType;

}
