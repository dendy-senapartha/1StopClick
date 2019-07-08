package com.domain.order.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.order.OrderDetailResult;
import com.domain.order.OrderResult;
import com.domain.order.repository.OrderRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find Track by productTitle
 */

public class GetOrderDetails extends UseCase<OrderDetailResult, GetOrderDetails.Params> {

    private final OrderRepository orderRepository;

    @Inject
    public GetOrderDetails(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           OrderRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.orderRepository = trackRepository;
    }

    @Override
    protected Observable<OrderDetailResult> buildUseCaseObservable(Params params) {
        return orderRepository.getOrderDetails(params);
    }

    public static class Params {
        public String authorization;
        public String orderId;

        private Params(String authorization, String userId) {
            this.authorization = authorization;
            this.orderId = userId;
        }

        public static Params forGetOrderDetails(String authorization, String userId) {
            return new Params(authorization, userId);
        }
    }
}
