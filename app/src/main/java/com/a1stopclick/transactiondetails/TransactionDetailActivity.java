package com.a1stopclick.transactiondetails;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerTransactionDetailsComponent;
import com.a1stopclick.dependencyinjection.components.MovieDetailsComponent;
import com.a1stopclick.dependencyinjection.components.TransactionDetailsComponent;
import com.a1stopclick.dependencyinjection.modules.MovieDetailsModule;
import com.a1stopclick.dependencyinjection.modules.TransactionDetailsModule;
import com.a1stopclick.moviedetails.MovieDetailContract;
import com.a1stopclick.util.AndroidUtils;
import com.alibaba.fastjson.JSON;

import com.domain.base.entity.OrderItem;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 10/05/2019.
 * transaction detail activity
 */

public class TransactionDetailActivity extends BaseActivity implements TransactionDetailContract.View {

    public static final String TRANSACTION_ORDER_ITEM = "transaction_order_item";

    @Inject
    TransactionDetailContract.Presenter presenter;

    private TransactionDetailsComponent component;

    OrderResult orderItem = null;

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

    @Override
    public int getLayout() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    public void init() {
        initComponent();
        loadData();
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
    public void onGetTransactionOrderDetailSuccess(OrderDetailResult result) {
        trRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trRecyclerView.setAdapter(new OrderItemRecyclerViewAdapter(this, result.orderItemList));
    }
}
