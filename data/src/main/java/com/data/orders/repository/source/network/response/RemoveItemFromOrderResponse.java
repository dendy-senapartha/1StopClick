package com.data.orders.repository.source.network.response;

import com.data.account.HTTPResponseHeader;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Order list response container
 */

public class RemoveItemFromOrderResponse {

    public String status;
    public String itemId;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

}
