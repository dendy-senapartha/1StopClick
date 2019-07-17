package com.data.orders.repository.source.network.response;

import com.data.account.HTTPResponseHeader;

/*
 * Created by dendy-prtha on 18/04/2019.
 */

public class PayingOrderResponse {

    public String status;
    public String message;
    public String orderId;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

}
