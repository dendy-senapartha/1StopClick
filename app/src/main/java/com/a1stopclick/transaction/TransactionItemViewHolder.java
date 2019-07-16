package com.a1stopclick.transaction;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;

import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.a1stopclick.transactiondetails.TransactionDetailActivity;
import com.alibaba.fastjson.JSON;
import com.domain.order.OrderResult;

public class TransactionItemViewHolder extends RecyclerView.ViewHolder {
    private TextView transactionTitle;
    private TextView transactionTotalPrice;
    private TextView transactionStatus;
    View itemView;
    Fragment parentFragment;

    public TransactionItemViewHolder(View itemView, Fragment parentFragment) {
        super(itemView);
        this.parentFragment = parentFragment;
        this.itemView = itemView;
        transactionTitle = itemView.findViewById(R.id.transactionTitle);
        transactionTotalPrice = itemView.findViewById(R.id.transactionTotalPrice);
        transactionStatus = itemView.findViewById(R.id.transactionStatus);
    }

    public void bind(OrderResult orderResult) {
        transactionTitle.setText(orderResult.order.orderTitle);
        transactionTotalPrice.setText("" + orderResult.order.totalAmount);
        transactionStatus.setText(orderResult.order.invoice.status);
        itemView.setOnClickListener(onClick -> {
                    Intent intent = new Intent(itemView.getContext(), TransactionDetailActivity.class);
                    intent.putExtra(TransactionDetailActivity.TRANSACTION_ORDER_ITEM, JSON.toJSONString(orderResult));
                    parentFragment.startActivityForResult(intent, FragmentInvoice.REFRESH_INVOICE_LIST);
                }
        );
    }

}
