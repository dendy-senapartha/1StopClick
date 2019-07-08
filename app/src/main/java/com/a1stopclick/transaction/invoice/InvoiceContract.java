package com.a1stopclick.transaction.invoice;

import com.a1stopclick.base.BaseContract.BasePresenterContract;
import com.domain.order.OrderResult;

import java.util.List;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface InvoiceContract {
    interface View
    {
        void onGetUserInvoiceSuccess(List<OrderResult> orderResultList);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenterContract
    {
        void initPresenter();
        void getUserInvoice();
    }
}
