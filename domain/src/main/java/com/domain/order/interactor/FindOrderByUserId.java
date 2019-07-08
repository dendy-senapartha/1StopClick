package com.domain.order.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.order.OrderResult;
import com.domain.order.repository.OrderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 27/05/2019.
 * find Track by productTitle
 */

public class FindOrderByUserId extends UseCase<List<OrderResult>, FindOrderByUserId.Params> {

    private final OrderRepository orderRepository;

    @Inject
    public FindOrderByUserId(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             OrderRepository trackRepository) {
        super(threadExecutor, postExecutionThread);
        this.orderRepository = trackRepository;
    }

    @Override
    protected Observable<List<OrderResult>> buildUseCaseObservable(Params params) {
        return orderRepository.findOrderByUserId(params);
    }

    public static class Params {
        public String authorization;
        public String userId;

        private Params(String authorization, String userId) {
            this.authorization = authorization;
            this.userId = userId;
        }

        public static Params forFindOrderByUserId(String authorization, String userId) {
            return new Params(authorization, userId);
        }
    }
}
