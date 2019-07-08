package com.data.orders.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.orders.OrderEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Order list response container
 */

public class FindOrderByUserIdResponse {

    public List<OrderEntity> orderEntityList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

}
