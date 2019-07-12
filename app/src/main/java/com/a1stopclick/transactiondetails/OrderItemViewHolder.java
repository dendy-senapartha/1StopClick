package com.a1stopclick.transactiondetails;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.OrderItem;

import com.domain.order.OrderResult;
import com.google.android.material.button.MaterialButton;


public class OrderItemViewHolder extends RecyclerView.ViewHolder {

    private  OrderResult orderDetail;
    private TextView orderItemTitle;
    private TextView orderItemPrice;
    private MaterialButton button;
    Context context;


    public OrderItemViewHolder(View itemView, OrderResult orderDetail, Context context) {
        super(itemView);
        orderItemTitle = itemView.findViewById(R.id.orderItemTitle);
        orderItemPrice = itemView.findViewById(R.id.orderItemPrice);
        button = itemView.findViewById(R.id.removeItem);
        this.context = context;
        this.orderDetail = orderDetail;
    }

    public void bind(OrderItem item) {
        orderItemTitle.setText(item.productName);
        orderItemPrice.setText(item.subtotal.toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (context instanceof TransactionDetailActivity) {
                    ((TransactionDetailActivity) context).getPresenter().removeItemFromOrder(orderDetail.order.id,item.productId,1);
                }
            }
        });
    }

}
