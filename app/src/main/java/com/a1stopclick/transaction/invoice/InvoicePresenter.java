package com.a1stopclick.transaction.invoice;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.a1stopclick.transaction.purchase.PurchaseContract;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.order.OrderResult;
import com.domain.order.interactor.GetUserOrderNeedToPay;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for purchase list
 */

public class InvoicePresenter implements InvoiceContract.Presenter {

    private final String TAG = InvoicePresenter.class.getSimpleName();

    private final Context context;

    private AccountResult userSession = null;

    private final InvoiceContract.View view;

    private final GetUserOrderNeedToPay getUserOrderNeedToPay;

    @Inject
    public InvoicePresenter(Context context,
                            InvoiceContract.View view, GetUserOrderNeedToPay getUserOrderNeedToPay) {
        this.context = context;
        this.view = view;
        this.getUserOrderNeedToPay = getUserOrderNeedToPay;
    }


    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void getUserInvoice() {
        view.setLoadingIndicator(true);
        getUserOrderNeedToPay.execute(new DefaultObserver<List<OrderResult>>(){
                                      @Override
                                      public  void onNext(List<OrderResult> results){
                                          view.setLoadingIndicator(false);
                                          view.onGetUserInvoiceSuccess(results);
                                      }

                                      @Override
                                      public void onError(Throwable er) {
                                          //TODO : need show error message based on error code from BE
                                          view.setLoadingIndicator(false);
                                      }
                                  },
                GetUserOrderNeedToPay.Params.forGetUserOrderNeedToPay(userSession.getAuthorization(),
                        userSession.getUid()));
    }

}
