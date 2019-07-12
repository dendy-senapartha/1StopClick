package com.domain.order.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.OrderDetailResult;
import com.domain.order.repository.OrderRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find Track by productTitle
 */

public class AddItemToOrder extends UseCase<AddItemToOrderResult, AddItemToOrder.Params> {

    private final OrderRepository orderRepository;

    @Inject
    public AddItemToOrder(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          OrderRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.orderRepository = trackRepository;
    }

    @Override
    protected Observable<AddItemToOrderResult> buildUseCaseObservable(Params params) {
        return orderRepository.addItemToOrder(params);
    }

    public static class Params {
        public String authorization;
        public String userId;
        public int productId;
        public int quantity;

        private Params(String authorization, String userId,
                       int productId, int quantity) {
            this.authorization = authorization;
            this.userId = userId;
            this.productId = productId;
            this.quantity = quantity;
        }

        public static Params forAddItemToOrder(String authorization, String userId,
                                               int productId, int quantity) {
            return new Params(authorization, userId, productId, quantity);
        }
    }
}
