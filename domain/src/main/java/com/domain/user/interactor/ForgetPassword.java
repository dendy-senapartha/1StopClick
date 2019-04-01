package com.domain.user.interactor;

import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.UseCase;
import com.domain.user.ForgetPasswordResult;
import com.domain.user.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget Password interactor
 */

public class ForgetPassword extends UseCase<ForgetPasswordResult, ForgetPassword.Params> {

    private final UserRepository userRepository;

    @Inject
    public ForgetPassword(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<ForgetPasswordResult> buildUseCaseObservable(Params params) {
        return userRepository.ForgetPassword(params);
    }

    public static class Params {
        public String email;

        private Params(String email) {
            this.email = email;

        }

        public static Params forgetPassword(String email) {
            return new Params(email);
        }
    }
}
