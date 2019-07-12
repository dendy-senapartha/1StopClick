package com.data.orders.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.orders.OrderEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Order list response container
 */

public class AddItemToOrderResponse {

    public String status;
    public String itemId;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

}
