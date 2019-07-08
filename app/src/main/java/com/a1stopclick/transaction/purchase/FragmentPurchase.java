package com.a1stopclick.transaction.purchase;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.ScrollChildSwipe;
import com.a1stopclick.dependencyinjection.components.DaggerPurchaseListComponent;
import com.a1stopclick.dependencyinjection.components.PurchaseListComponent;
import com.a1stopclick.dependencyinjection.modules.PurchaseListModule;
import com.a1stopclick.transaction.TransactionRecyclerViewAdapter;
import com.domain.order.OrderResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 12/06/2019.
 * fragment purchase
 */

public class FragmentPurchase extends BaseFragment implements PurchaseContract.View {

    private PurchaseListComponent component;

    @Inject
    PurchaseContract.Presenter presenter;

    @BindView(R.id.paymentRecycleView)
    RecyclerView recyclerView;

    @BindView(R.id.paymentRefreshLayout)
    ScrollChildSwipe swipeRefreshLayout;

    @BindView(R.id.paymentNoText)
    TextView noPaymentText;

    private TransactionRecyclerViewAdapter transactionRecyclerViewAdapter;

    public FragmentPurchase() {
        super();
        setFragmentTitle("Purchase");
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void init() {
        initComponent();
        prepareRefreshLayout();
        presenter.getUserPurchase();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerPurchaseListComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .purchaseListModule(new PurchaseListModule(this))
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
            presenter.getUserPurchase();
        });
        transactionRecyclerViewAdapter = new TransactionRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(transactionRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        transactionRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetUserPurchaseSuccess(List<OrderResult> orderResultList) {
        if (orderResultList.size() == 0) {
            noPaymentText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            transactionRecyclerViewAdapter.setItems(orderResultList);
            transactionRecyclerViewAdapter.notifyDataSetChanged();
            noPaymentText.setVisibility(View.GONE);
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
