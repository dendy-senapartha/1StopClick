package com.domain.order.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.RemoveItemFromOrderResult;
import com.domain.order.repository.OrderRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 *
 */

public class RemoveItemFromOrder extends UseCase<RemoveItemFromOrderResult, RemoveItemFromOrder.Params> {

    private final OrderRepository orderRepository;

    @Inject
    public RemoveItemFromOrder(ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread,
                               OrderRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.orderRepository = trackRepository;
    }

    @Override
    protected Observable<RemoveItemFromOrderResult> buildUseCaseObservable(Params params) {
        return orderRepository.removeItemFromOrder(params);
    }

    public static class Params {
        public String authorization;
        public String orderId;
        public int productId;
        public int quantity;

        private Params(String authorization, String orderId,
                       int productId, int quantity) {
            this.authorization = authorization;
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
        }

        public static Params forRemoveItemFromOrder(String authorization, String orderId,
                                               int productId, int quantity) {
            return new Params(authorization, orderId, productId, quantity);
        }
    }
}
