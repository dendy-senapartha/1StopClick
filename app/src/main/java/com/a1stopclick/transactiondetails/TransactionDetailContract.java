package com.a1stopclick.transactiondetails;

import com.a1stopclick.base.BaseContract;
import com.domain.order.OrderDetailResult;
import com.domain.product.ProductResult;
import com.domain.video.VideoResult;

import java.util.List;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie Detail Contract
 */

public interface TransactionDetailContract {
    interface View extends BaseContract.BaseViewContract {
        void onGetTransactionOrderDetailSuccess(OrderDetailResult result);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void initPresenter();

        void getTransactionOrderDetail(String orderId);

    }
}
