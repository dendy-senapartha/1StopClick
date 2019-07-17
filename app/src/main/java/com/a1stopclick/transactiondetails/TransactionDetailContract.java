package com.a1stopclick.transactiondetails;

import com.a1stopclick.base.BaseContract;
import com.domain.base.entity.OrderItem;
import com.domain.order.OrderDetailResult;
import com.domain.order.PayingOrderResult;
import com.domain.order.RemoveItemFromOrderResult;
import com.domain.product.ProductResult;
import com.domain.video.VideoResult;

import java.util.List;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie Detail Contract
 */

public interface TransactionDetailContract {
    interface View extends BaseContract.BaseViewContract {
        void onGetTransactionOrderDetailSuccess(List<OrderItem> result);

        void onRemoveItemFromOrderSuccess(RemoveItemFromOrderResult result);

        void onPayingOrderSuccess(PayingOrderResult result);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void initPresenter();

        void removeItemFromOrder(int orderId, int productId, int quantity);

        void getTransactionOrderDetail(String orderId);

        void payingOrder(int orderId, int paymentMehtodId);
    }
}
