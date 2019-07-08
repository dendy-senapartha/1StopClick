package com.a1stopclick.transaction.purchase;

import com.a1stopclick.base.BaseContract.BasePresenterContract;
import com.domain.order.OrderResult;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface PurchaseContract {
    interface View
    {
        void onGetUserPurchaseSuccess(List<OrderResult> orderResultList);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenterContract
    {
        void initPresenter();
        void getUserPurchase();
    }
}
