package com.a1stopclick.transactiondetails;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerTransactionDetailsComponent;
import com.a1stopclick.dependencyinjection.components.TransactionDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.TransactionDetailsModule;
import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.alibaba.fastjson.JSON;

import com.domain.base.entity.OrderItem;
import com.domain.order.OrderResult;
import com.domain.order.PayingOrderResult;
import com.domain.order.RemoveItemFromOrderResult;
import com.google.android.material.button.MaterialButton;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 10/05/2019.
 * transaction detail activity
 */

public class TransactionDetailActivity extends BaseActivity implements TransactionDetailContract.View {

    public static final String TRANSACTION_ORDER_ITEM = "transaction_order_item";

    @Inject
    TransactionDetailContract.Presenter presenter;

    private TransactionDetailsComponent component;

    @BindView(R.id.orderDate)
    TextView orderDate;

    @BindView(R.id.paymentStatus)
    TextView paymentStatus;

    @BindView(R.id.paymentMethod)
    TextView paymentMethod;

    @BindView(R.id.totalAmount)
    TextView totalAmount;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.transactionOrderItems)
    RecyclerView trRecyclerView;

    @BindView(R.id.transactionToolbar)
    Toolbar toolbar;

    @BindView(R.id.payButton)
    MaterialButton payButton;

    private List<OrderItem> orderItemList;

    OrderResult orderItem = null;

    @Override
    public int getLayout() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    public void init() {
        initComponent();
        loadData();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerTransactionDetailsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .transactionDetailsModule(new TransactionDetailsModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        String order = getIntent().getStringExtra(TRANSACTION_ORDER_ITEM);
        orderItem = JSON.parseObject(order, OrderResult.class);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
        orderDate.setText(sdf.format(orderItem.order.orderDate));
        paymentStatus.setText(orderItem.order.invoice.status);
        paymentMethod.setText(orderItem.order.invoice.paymentMethod.name);
        description.setText(orderItem.order.invoice.description);
        totalAmount.setText(orderItem.order.totalAmount.toString());
        presenter.getTransactionOrderDetail(orderItem.order.id + "");

        if (orderItem.order.invoice.status.equalsIgnoreCase("DRAFT")) {
            payButton.setVisibility(View.VISIBLE);
        } else {
            payButton.setVisibility(View.GONE);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    public void onGetTransactionOrderDetailSuccess(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
        trRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trRecyclerView.setAdapter(new OrderItemRecyclerViewAdapter(this, orderItem, orderItemList));
    }

    @Override
    public void onRemoveItemFromOrderSuccess(RemoveItemFromOrderResult result) {
        if (Boolean.parseBoolean(result.status)) {
            //remove the song from the album list
            for (Iterator<OrderItem> songOfTheAlbumIterator = orderItemList.iterator();
                 songOfTheAlbumIterator.hasNext(); ) {
                OrderItem orderedItem = songOfTheAlbumIterator.next();
                if (result.itemId.equalsIgnoreCase(orderedItem.productId + "")) {
                    orderItem.order.totalAmount = orderItem.order.totalAmount.subtract(orderedItem.subtotal);
                    totalAmount.setText(orderItem.order.totalAmount + "");
                    songOfTheAlbumIterator.remove();
                }
            }
            //close the activity if the order item already zero
            onGetTransactionOrderDetailSuccess(orderItemList);
            if (orderItemList.isEmpty()) {
                exitAndRefreshInvoiceList();
            }
        }
    }

    private void exitAndRefreshInvoiceList()
    {
        setResult(FragmentInvoice.REFRESH_INVOICE_LIST, new Intent());
        finish();
    }

    @Override
    public void onPayingOrderSuccess(PayingOrderResult result) {
        exitAndRefreshInvoiceList();
    }

    @OnClick(R.id.payButton)
    public void payButtonOnClick(View view) {
        presenter.payingOrder(orderItem.order.id,orderItem.order.invoice.paymentMethod.id);
    }

    public TransactionDetailContract.Presenter getPresenter() {
        return presenter;
    }
}
