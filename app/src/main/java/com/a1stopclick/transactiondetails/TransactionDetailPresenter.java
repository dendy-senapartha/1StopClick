package com.a1stopclick.transactiondetails;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.order.OrderDetailResult;
import com.domain.order.interactor.GetOrderDetails;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class TransactionDetailPresenter implements TransactionDetailContract.Presenter {

    private final String TAG = TransactionDetailPresenter.class.getSimpleName();

    private final Context context;

    private final TransactionDetailContract.View view;

    private final GetOrderDetails getOrderDetails;

    private AccountResult userSession = null;

    @Inject
    public TransactionDetailPresenter(Context context, TransactionDetailContract.View view,
                                      GetOrderDetails getOrderDetails) {
        this.context = context;
        this.view = view;
        this.getOrderDetails = getOrderDetails;
    }

    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void getTransactionOrderDetail(String orderId) {
        getOrderDetails.execute(new DefaultObserver<OrderDetailResult>() {
                                    @Override
                                    public void onNext(OrderDetailResult result) {
                                        view.onGetTransactionOrderDetailSuccess(result);
                                    }

                                    @Override
                                    public void onError(Throwable er) {
                                        //TODO : need show error message based on error code from BE

                                    }
                                },
                GetOrderDetails.Params.forGetOrderDetails(userSession.getAuthorization(),orderId)
        );
    }


    @Override
    public void onDestroy() {

    }
}
