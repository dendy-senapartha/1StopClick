package com.domain.order.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.PayingOrderResult;
import com.domain.order.repository.OrderRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 *
 */

public class PayingOrder extends UseCase<PayingOrderResult, PayingOrder.Params> {

    private final OrderRepository orderRepository;

    @Inject
    public PayingOrder(ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       OrderRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.orderRepository = trackRepository;
    }

    @Override
    protected Observable<PayingOrderResult> buildUseCaseObservable(Params params) {
        return orderRepository.payingOrder(params);
    }

    public static class Params {
        public String authorization;
        public String userId;
        public String orderId;
        public String paymentMethodId;

        private Params(String authorization, String userId,
                       String orderId, String paymentMethodId) {
            this.authorization = authorization;
            this.userId = userId;
            this.orderId = orderId;
            this.paymentMethodId = paymentMethodId;
        }

        public static Params forPayingOrder(String authorization, String userId,
                                            String orderId, String paymentMethodId) {
            return new Params(authorization, userId, orderId, paymentMethodId);
        }
    }
}
