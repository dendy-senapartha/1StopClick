package com.a1stopclick.transaction.purchase;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.order.OrderResult;
import com.domain.order.interactor.FindOrderByUserId;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for purchase list
 */

public class PurchasePresenter implements PurchaseContract.Presenter {

    private final String TAG = PurchasePresenter.class.getSimpleName();

    private final Context context;

    private AccountResult userSession = null;

    private final PurchaseContract.View view;

    private final FindOrderByUserId findOrderByUserId;

    @Inject
    public PurchasePresenter(Context context,
                             PurchaseContract.View view, FindOrderByUserId findOrderByUserId) {
        this.context = context;
        this.view = view;
        this.findOrderByUserId = findOrderByUserId;
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
    public void getUserPurchase() {
        view.setLoadingIndicator(true);
        findOrderByUserId.execute(new DefaultObserver<List<OrderResult>>(){
                                   @Override
                                   public  void onNext(List<OrderResult> results){
                                       view.setLoadingIndicator(false);
                                       view.onGetUserPurchaseSuccess(results);
                                   }

                                   @Override
                                   public void onError(Throwable er) {
                                       //TODO : need show error message based on error code from BE
                                       view.setLoadingIndicator(false);
                                   }
                               },
                FindOrderByUserId.Params.forFindOrderByUserId(userSession.getAuthorization(), userSession.getUid()));
    }
}
