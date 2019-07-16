package com.a1stopclick.transaction.invoice;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerInvoiceListComponent;
import com.a1stopclick.dependencyinjection.components.InvoiceListComponent;
import com.a1stopclick.dependencyinjection.modules.InvoiceListModule;
import com.a1stopclick.transaction.TransactionRecyclerViewAdapter;
import com.domain.order.OrderResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.app.Activity.*;

/*
 * Created by dendy-prtha on 12/06/2019.
 * fragment invoice
 */

public class FragmentInvoice extends BaseFragment implements InvoiceContract.View {

    private InvoiceListComponent component;

    public final static int REFRESH_INVOICE_LIST = 1;

    @Inject
    InvoiceContract.Presenter presenter;

    @BindView(R.id.invoiceRecycleView)
    RecyclerView recyclerView;

    @BindView(R.id.invoiceRefreshLayout)
    ScrollChildSwipe swipeRefreshLayout;

    @BindView(R.id.noInvoiceText)
    TextView noInvoiceText;

    private TransactionRecyclerViewAdapter transactionRecyclerViewAdapter;

    public FragmentInvoice() {
        super();
        setFragmentTitle("Invoice");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == REFRESH_INVOICE_LIST) {
            if(resultCode == RESULT_FIRST_USER)
            {
                presenter.getUserInvoice();
            }
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_invoice;
    }

    @Override
    public void init() {
        initComponent();
        prepareRefreshLayout();
        presenter.getUserInvoice();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerInvoiceListComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .invoiceListModule(new InvoiceListModule(this))
                    .build();
        }
        component.inject(this);

        presenter.initPresenter();
        registerPresenter(presenter);
    }

    private void prepareRefreshLayout() {
        // Set up progress indicator
        /**/
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getUserInvoice();
        });
        transactionRecyclerViewAdapter = new TransactionRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(transactionRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        transactionRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetUserInvoiceSuccess(List<OrderResult> orderResultList) {
        if (orderResultList.size() == 0) {
            noInvoiceText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            transactionRecyclerViewAdapter.setItems(orderResultList);
            transactionRecyclerViewAdapter.notifyDataSetChanged();
            noInvoiceText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }
        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(active));
    }
}
