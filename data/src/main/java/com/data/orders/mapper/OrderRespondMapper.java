package com.data.orders.mapper;

import com.data.orders.OrderDetailsEntity;
import com.data.orders.OrderEntity;
import com.data.orders.repository.source.network.response.FindOrderByUserIdResponse;
import com.data.orders.repository.source.network.response.GetOrderIdDetailsResponse;
import com.domain.base.entity.Invoice;
import com.domain.base.entity.Order;
import com.domain.base.entity.OrderItem;
import com.domain.base.entity.PaymentMethod;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Album list response mapper
 */

@Singleton
public class OrderRespondMapper {

    @Inject
    public OrderRespondMapper() {

    }

    public List<OrderResult> transformOrderList(FindOrderByUserIdResponse response) {
        List<OrderResult> result = new ArrayList<>();
        for (OrderEntity orderEntity : response.orderEntityList) {
            OrderResult orderResult = new OrderResult();
            orderResult.order = new Order();
            orderResult.order.id = orderEntity.id;
            orderResult.order.orderTitle = orderEntity.orderTitle;
            orderResult.order.orderDate = orderEntity.orderDate;
            orderResult.order.totalAmount = orderEntity.totalAmount;

            if (orderEntity.invoice != null) {
                orderResult.order.invoice = new Invoice();
                orderResult.order.invoice.id = orderEntity.invoice.id;
                orderResult.order.invoice.created = orderEntity.invoice.created;
                orderResult.order.invoice.description = orderEntity.invoice.description;
                orderResult.order.invoice.status = orderEntity.invoice.status;
                if (orderEntity.invoice.paymentMethod != null) {
                    orderResult.order.invoice.paymentMethod = new PaymentMethod();
                    orderResult.order.invoice.paymentMethod.id = orderEntity.invoice.paymentMethod.id;
                    orderResult.order.invoice.paymentMethod.code = orderEntity.invoice.paymentMethod.code;
                    orderResult.order.invoice.paymentMethod.name = orderEntity.invoice.paymentMethod.name;
                }
            }
            result.add(orderResult);
        }
        return result;
    }

    public OrderDetailResult transformOrderDetails(GetOrderIdDetailsResponse response) {
        OrderDetailResult result = new OrderDetailResult();
        result.id = response.orderDetailsEntity.id;
        result.orderDate = response.orderDetailsEntity.orderDate;
        result.totalAmount = response.orderDetailsEntity.totalAmount;

        if (result.invoice != null) {
            result.invoice = new Invoice();
            result.invoice.id = response.orderDetailsEntity.invoice.id;
            result.invoice.created = response.orderDetailsEntity.invoice.created;
            result.invoice.description = response.orderDetailsEntity.invoice.description;
            result.invoice.status = response.orderDetailsEntity.invoice.status;
            if (result.invoice.paymentMethod != null) {
                result.invoice.paymentMethod = new PaymentMethod();
                result.invoice.paymentMethod.id = response.orderDetailsEntity.invoice.paymentMethod.id;
                result.invoice.paymentMethod.code = response.orderDetailsEntity.invoice.paymentMethod.code;
                result.invoice.paymentMethod.name = response.orderDetailsEntity.invoice.paymentMethod.name;
            }
        }

        result.orderItemList = new ArrayList<>();
        for (OrderDetailsEntity.OrderItem orderItem : response.orderDetailsEntity.orderItemList) {
            OrderItem resultOrderItem = new OrderItem();
            resultOrderItem.productId = Integer.parseInt(orderItem.productId);
            resultOrderItem.productName = orderItem.productName;
            resultOrderItem.quantity = Integer.parseInt(orderItem.quantity);
            resultOrderItem.subtotal = new BigDecimal(orderItem.subtotal);

            result.orderItemList.add(resultOrderItem);
        }
        return result;
    }
}
