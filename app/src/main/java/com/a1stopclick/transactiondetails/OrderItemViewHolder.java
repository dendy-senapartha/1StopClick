package com.a1stopclick.transactiondetails;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.domain.base.entity.OrderItem;

import com.domain.base.result.AlbumResult;


public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    private TextView orderItemTitle;
    private TextView orderItemPrice;
    Context context;


    public OrderItemViewHolder(View itemView, Context context) {
        super(itemView);
        orderItemTitle = itemView.findViewById(R.id.orderItemTitle);
        orderItemPrice = itemView.findViewById(R.id.orderItemPrice);
        this.context = context;
    }

    public void bind(OrderItem item) {
        orderItemTitle.setText(item.productName);
        orderItemPrice.setText(item.subtotal.toString());
    }

}
